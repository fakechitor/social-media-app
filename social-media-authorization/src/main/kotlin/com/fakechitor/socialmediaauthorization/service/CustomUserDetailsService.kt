package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.config.security.CustomUserDetails
import com.fakechitor.socialmediaauthorization.utils.Utils.mapToUserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userServiceClient: UserServiceClient,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): CustomUserDetails =
        userServiceClient
            .getUserByUsername(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")
}
