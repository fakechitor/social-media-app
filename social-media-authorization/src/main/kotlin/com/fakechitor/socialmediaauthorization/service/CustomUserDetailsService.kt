package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.response.UserResponseDto
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userServiceClient: UserServiceClient,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails =
        userServiceClient
            .getUserByUsername(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")

    private fun UserResponseDto.mapToUserDetails(): UserDetails =
        User(
            this.username,
            this.password,
            listOf(),
        )
}
