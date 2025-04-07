package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.response.UserInternalDto
import com.fakechitor.socialmediaauthorization.exception.UserAlreadyExistsException
import com.fakechitor.socialmediaauthorization.exception.UserNotFoundException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException
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
            .onStatus(HttpStatus.NOT_FOUND::equals) { throw UserNotFoundException("User not found") }
            .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals) { throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR) }
            .bodyToMono(UserInternalDto::class.java)
            .block()

    fun saveUser(userRegisterDto: UserRegisterDto): UserInternalDto =
        webClient
            .post()
            .uri("$userAddress")
            .header(internalSecretHeader, internalSecretToken)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(userRegisterDto)
            .retrieve()
            .onStatus(HttpStatus.CONFLICT::equals) { throw UserAlreadyExistsException("User already exists") }
            .onStatus(HttpStatus.INTERNAL_SERVER_ERROR::equals) { throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR) }
            .bodyToMono(UserInternalDto::class.java)
            .block() ?: throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)
}
