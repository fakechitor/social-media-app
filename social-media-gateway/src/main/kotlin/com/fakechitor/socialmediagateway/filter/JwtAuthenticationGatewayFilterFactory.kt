package com.fakechitor.socialmediagateway.filter

import com.fakechitor.socialmediagateway.client.AuthClient
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationGatewayFilterFactory(
    private val authClient: AuthClient,
) : AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilterFactory.Config>(Config::class.java) {
    override fun apply(config: Config): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val token = getJwtToken(exchange.request)
            if (token.isNullOrEmpty() || !authClient.validateToken(token)) {
                return@GatewayFilter exchange.response.run {
                    statusCode = HttpStatus.UNAUTHORIZED
                    headers.contentType = MediaType.APPLICATION_JSON
                    writeWith(getBufferWithJson())
                }
            }
            chain.filter(exchange)
        }

    class Config
}

private fun getJwtToken(request: ServerHttpRequest): String? {
    val header = request.headers[AUTHORIZATION]?.first()
    return when (header?.startsWith("Bearer ")) {
        true -> header.removePrefix("Bearer ")
        else -> null
    }
}

private fun ServerHttpResponse.getBufferWithJson(): Mono<DataBuffer> {
    val bufferFactory: DataBufferFactory = this.bufferFactory()

    val jsonBytes = jacksonObjectMapper().writeValueAsBytes(mapOf("message" to "Token is missing or invalid"))
    val buffer = bufferFactory.wrap(jsonBytes)
    return Mono.just(buffer)
}
