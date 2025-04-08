package com.fakechitor.socialmediaauthorization.utils

import com.fakechitor.socialmediaauthorization.dto.response.UserInternalDto
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

object Utils {
    fun UserInternalDto.mapToUserDetails(): UserDetails =
        User(
            this.username,
            this.password,
            listOf(),
        )
}
