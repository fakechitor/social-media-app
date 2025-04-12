package com.fakechitor.socialmediaauthorization.controller

import com.fakechitor.socialmediaauthorization.docs.jwt.RefreshTokenDocs
import com.fakechitor.socialmediaauthorization.docs.jwt.ValidateTokenDocs
import com.fakechitor.socialmediaauthorization.dto.request.RefreshTokenRequest
import com.fakechitor.socialmediaauthorization.dto.response.TokenResponse
import com.fakechitor.socialmediaauthorization.service.AuthService
import com.fakechitor.socialmediaauthorization.service.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class JwtController(
    private val authService: AuthService,
    private val tokenService: TokenService,
) {
    @GetMapping("/validate")
    @ValidateTokenDocs
    fun validateToken(
        @RequestParam("token") token: String,
    ) = ResponseEntity.ok(tokenService.validateJwtToken(token))

    @PostMapping("/refresh")
    @RefreshTokenDocs
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequest,
    ): TokenResponse =
        authService
            .refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token.")

    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this,
        )
}
