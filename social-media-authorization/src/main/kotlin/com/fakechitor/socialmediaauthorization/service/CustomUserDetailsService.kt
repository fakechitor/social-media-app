package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.UserResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class CustomUserDetailsService(
    private val webClient: WebClient,
) : UserDetailsService {
    @Value("\${util.users-address}")
    private var userAddress: String? = null

    override fun loadUserByUsername(username: String?): UserDetails {
        val userRequest = webClient.get().uri("$userAddress/username/$username")
        return userRequest
            .retrieve()
            .bodyToMono(UserResponseDto::class.java)
            .block()
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found")
    }

    private fun UserResponseDto.mapToUserDetails(): UserDetails =
        User(
            this.username,
            this.password,
            listOf(),
        )
}
