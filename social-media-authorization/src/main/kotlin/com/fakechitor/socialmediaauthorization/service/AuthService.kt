package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.response.UserInternalDto
import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.property.JwtProperties
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
) {
    fun authentication(authenticationRequest: UserLoginDto): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.login,
                authenticationRequest.password,
            ),
        )
        val user = userDetailsService.loadUserByUsername(authenticationRequest.login)
        return AuthenticationResponse(
            accessToken = createAccessToken(user),
        )
    }

    private fun createAccessToken(user: UserDetails) =
        tokenService.generate(
            userDetails = user,
            expirationDate = getAccessTokenExpiration(),
        )

    private fun getAccessTokenExpiration(): Date = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    fun register(userRegisterDto: UserRegisterDto): UserInternalDto? {
        val encryptedPasswordDto =
            UserRegisterDto(userRegisterDto.email, userRegisterDto.login, passwordEncoder.encode(userRegisterDto.password))
        return userServiceClient.saveUser(encryptedPasswordDto)
    }
}
