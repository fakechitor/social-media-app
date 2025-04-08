package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.property.JwtProperties
import com.fakechitor.socialmediaauthorization.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun authentication(userLoginDto: UserLoginDto): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userLoginDto.login,
                userLoginDto.password,
            ),
        )
        return generateAndSaveTokens(user = userDetailsService.loadUserByUsername(userLoginDto.login))
    }

    fun generateAndSaveTokens(user: UserDetails): AuthenticationResponse {
        val accessToken = createAccessToken(user)
        val refreshToken = createRefreshToken(user)
        refreshTokenRepository.saveToken(refreshToken, user.username)
        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    fun refreshAccessToken(refreshToken: String): String? {
        val extractedEmail = tokenService.extractEmail(refreshToken)
        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUsername = refreshTokenRepository.getUserDetailsByToken(refreshToken)
            if (!tokenService.isExpired(refreshToken) && refreshTokenUsername == currentUserDetails.username) {
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
}
