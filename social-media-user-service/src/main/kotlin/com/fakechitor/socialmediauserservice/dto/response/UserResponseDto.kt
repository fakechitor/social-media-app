package com.fakechitor.socialmediauserservice.dto.response

data class UserResponseDto(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
)
