package com.fakechitor.socialmediaauthorization.dto.response

data class UserInternalDto(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
)
