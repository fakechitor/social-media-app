package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.config.security.CustomUserDetails
import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.property.JwtProperties
import com.fakechitor.socialmediaauthorization.repository.RefreshTokenRepository
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
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
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun authentication(userLoginDto: UserLoginDto): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userLoginDto.login,
                userLoginDto.password,
            ),
        )
        return generateAndSaveTokens(user = userDetailsService.loadUserByUsername(userLoginDto.login))
    }

    fun generateAndSaveTokens(user: CustomUserDetails): AuthenticationResponse {
        val accessToken = createAccessToken(user)
        val refreshToken = createRefreshToken(user)
        if (user.username != null) {
            refreshTokenRepository.saveToken(refreshToken, user.username!!)
            logger.info("Refresh token saved for ${user.username}")
        }

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

    private fun createAccessToken(user: CustomUserDetails) =
        tokenService.generate(
            userDetails = user,
            expirationDate = getAccessTokenExpiration(),
        )

    private fun createRefreshToken(user: CustomUserDetails) =
        tokenService.generate(
            userDetails = user,
            expirationDate = getRefreshTokenExpiration(),
        )

    private fun getAccessTokenExpiration(): Date = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    private fun getRefreshTokenExpiration(): Date = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
}
