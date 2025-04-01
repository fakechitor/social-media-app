package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.UserResponseDto
import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.property.JwtProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import java.util.*

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val webClient: WebClient,
    private val passwordEncoder: PasswordEncoder,
) {
    @Value("\${util.users-address}")
    private var userAddress: String? = null

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

    fun register(userRegisterDto: UserRegisterDto): UserResponseDto? {
        val encryptedPasswordDto =
            UserRegisterDto(userRegisterDto.email, userRegisterDto.login, passwordEncoder.encode(userRegisterDto.password))
        return webClient
            .post()
            .uri("$userAddress")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(encryptedPasswordDto)
            .retrieve()
            .bodyToMono(UserResponseDto::class.java)
            .block()
    }
}
