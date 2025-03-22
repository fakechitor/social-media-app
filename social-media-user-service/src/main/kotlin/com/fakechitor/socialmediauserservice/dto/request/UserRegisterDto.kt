package com.fakechitor.socialmediauserservice.dto.request

data class UserRegisterDto(
    val email: String,
    val login: String,
    val password: String,
)
