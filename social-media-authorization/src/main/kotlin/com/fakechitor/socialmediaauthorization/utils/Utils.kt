package com.fakechitor.socialmediaauthorization.utils

import com.fakechitor.socialmediaauthorization.config.security.CustomUserDetails
import com.fakechitor.socialmediaauthorization.dto.response.UserInternalDto

object Utils {
    fun UserInternalDto.mapToUserDetails(): CustomUserDetails =
        CustomUserDetails(
            this.id,
            this.username,
            this.password,
            listOf(),
        )
}
