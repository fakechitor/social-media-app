package com.fakechitor.socialmediaauthorization.service

import org.springframework.beans.factory.annotation.Value
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
        val userInfo = webClient.get().uri(userAddress + username)
        return userInfo.retrieve().bodyToMono(UserDetails::class.java).block()
            ?: throw UsernameNotFoundException("User $username not found")
    }
}
