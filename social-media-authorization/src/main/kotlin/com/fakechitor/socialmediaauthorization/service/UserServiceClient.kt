package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.response.UserInternalDto
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

    fun getUserByUsername(username: String?): UserInternalDto? =
        webClient
            .get()
            .uri("$userAddress/username/$username")
            .header(internalSecretHeader, internalSecretToken)
            .retrieve()
            .bodyToMono(UserInternalDto::class.java)
            .block()

    fun saveUser(userRegisterDto: UserRegisterDto): UserInternalDto? =
        webClient
            .post()
            .uri("$userAddress")
            .header(internalSecretHeader, internalSecretToken)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(userRegisterDto)
            .retrieve()
            .bodyToMono(UserInternalDto::class.java)
            .block()
}
