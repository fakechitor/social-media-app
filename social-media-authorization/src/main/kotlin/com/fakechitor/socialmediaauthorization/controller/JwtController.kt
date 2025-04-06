package com.fakechitor.socialmediaauthorization.controller

import com.fakechitor.socialmediaauthorization.dto.request.RefreshTokenRequest
import com.fakechitor.socialmediaauthorization.dto.response.TokenResponse
import com.fakechitor.socialmediaauthorization.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class JwtController(
    private val authService: AuthService,
) {
    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest,
    ): TokenResponse =
        authService
            .refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this,
        )
}
