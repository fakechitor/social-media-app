package com.fakechitor.socialmediauserservice.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtUtils {
    @Value("\${jwt.key}")
    private lateinit var secretToken: String

    fun getUserId(authHeader: String): Long = extractAllClaimsFromJwt(authHeader).get("userId", Number::class.java).toLong()

    private fun extractAllClaimsFromJwt(token: String): Claims {
        val jwt = token.substring(7)
        return Jwts
            .parser()
            .verifyWith(parseStringToSecretToken(secretToken))
            .build()
            .parseSignedClaims(jwt)
            .payload
    }

    private fun parseStringToSecretToken(secretToken: String) = Keys.hmacShaKeyFor(secretToken.toByteArray(Charsets.UTF_8))
}
