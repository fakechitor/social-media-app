package com.fakechitor.socialmediaauthorization.dto.response

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String,
)
