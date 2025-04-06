package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.dto.response.UserInternalDto
import com.fakechitor.socialmediaauthorization.property.JwtProperties
import com.fakechitor.socialmediaauthorization.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val passwordEncoder: PasswordEncoder,
    private val userServiceClient: UserServiceClient,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun authentication(userLoginDto: UserLoginDto): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userLoginDto.login,
                userLoginDto.password,
            ),
        )
        val user = userDetailsService.loadUserByUsername(userLoginDto.login)
        val accessToken = createAccessToken(user)
        val refreshToken = createRefreshToken(user)
        refreshTokenRepository.saveToken(refreshToken, user)
        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    fun refreshAccessToken(refreshToken: String): String? {
        val extractedEmail = tokenService.extractEmail(refreshToken)
        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.getUserDetailsByToken(refreshToken)
            if (!tokenService.isExpired(refreshToken) && refreshTokenUserDetails?.username == currentUserDetails.username) {
                createAccessToken(currentUserDetails)
            } else {
                null
            }
        }
    }

    private fun createAccessToken(user: UserDetails) =
        tokenService.generate(
            userDetails = user,
            expirationDate = getAccessTokenExpiration(),
        )

    private fun createRefreshToken(user: UserDetails) =
        tokenService.generate(
            userDetails = user,
            expirationDate = getRefreshTokenExpiration(),
        )

    private fun getAccessTokenExpiration(): Date = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    private fun getRefreshTokenExpiration(): Date = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)

    fun register(userRegisterDto: UserRegisterDto): UserInternalDto? {
        val encryptedPasswordDto =
            UserRegisterDto(userRegisterDto.email, userRegisterDto.login, passwordEncoder.encode(userRegisterDto.password))
        return userServiceClient.saveUser(encryptedPasswordDto)
    }
}
