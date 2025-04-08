package com.fakechitor.socialmediagateway.client

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class AuthClient(
    private val restTemplate: RestTemplate,
) {
    fun validateToken(token: String): Boolean {
        val baseUri = "http:localhost:8082/api/auth/validate"
        val url =
            UriComponentsBuilder
                .fromUriString(baseUri)
                .queryParam("token", token)
                .toUriString()
        val response =
            restTemplate.getForObject(url, String::class.java)
        return true
    }
}
