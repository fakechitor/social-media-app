package com.fakechitor.socialmediaauthorization.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpiration: Int,
    val refreshTokenExpiration: Int,
)
