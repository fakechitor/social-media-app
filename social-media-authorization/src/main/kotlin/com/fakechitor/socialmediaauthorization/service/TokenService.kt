package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.property.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    private val jwtProperties: JwtProperties,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    private val secretKey =
        Keys.hmacShaKeyFor(
            jwtProperties.key.toByteArray(),
        )

    fun validateJwtToken(authToken: String?) {
        runCatching {
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parse(authToken)
        }.onFailure {
            logger.info("Error with validating jwt token: $authToken")
            throw JwtException("Jwt token validation error")
        }
    }

    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap(),
    ): String =
        Jwts
            .builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    fun isValid(
        token: String,
        userDetails: UserDetails,
    ): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }

    fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    private fun getAllClaims(token: String): Claims {
        val parser =
            Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
        return parser
            .parseSignedClaims(token)
            .payload
    }
}
