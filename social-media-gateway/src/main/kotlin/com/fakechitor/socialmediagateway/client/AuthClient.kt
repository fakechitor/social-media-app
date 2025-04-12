package com.fakechitor.socialmediagateway.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class AuthClient(
    private val webClient: WebClient,
) {
    @Value("\${util.auth-address}")
    private var authAddress: String? = null

    fun validateToken(token: String): Boolean {
        runCatching {
            webClient
                .get()
                .uri("$authAddress/api/auth/validate?token=$token")
                .retrieve()
                .toBodilessEntity()
                .block()
        }.fold(
            onSuccess = { return true },
            onFailure = { return false },
        )
    }
}
