package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.UserResponseDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class UserServiceClient(
    private val webClient: WebClient,
) {
    @Value("\${util.users-address}")
    private var userAddress: String? = null

    @Value("\${util.internal-secret-token}")
    private var internalSecretToken: String? = null

    private val internalSecretHeader = "X-Internal-Secret"

    fun getUserByUsername(username: String?): UserResponseDto? =
        webClient
            .get()
            .uri("$userAddress/username/$username")
            .header(internalSecretHeader, internalSecretToken)
            .retrieve()
            .bodyToMono(UserResponseDto::class.java)
            .block()

    fun saveUser(userRegisterDto: UserRegisterDto): UserResponseDto? =
        webClient
            .post()
            .uri("$userAddress")
            .header(internalSecretHeader, internalSecretToken)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(userRegisterDto)
            .retrieve()
            .bodyToMono(UserResponseDto::class.java)
            .block()
}
