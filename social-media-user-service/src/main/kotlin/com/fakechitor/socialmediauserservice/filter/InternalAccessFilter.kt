package com.fakechitor.socialmediauserservice.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class InternalOnlyFilter(
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {
    @Value("\${utils.internal-secret-token}")
    lateinit var internalToken: String

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (request.requestURI.startsWith("/api/users/internal/")) {
            val secret = request.getHeader("X-Internal-Secret")
            if (secret != internalToken) {
                response.status = HttpStatus.FORBIDDEN.value()
                response.contentType = MediaType.APPLICATION_JSON_VALUE
                response.writer.write(
                    objectMapper.writeValueAsString(
                        mapOf("message" to "You do not have permission to access that endpoint"),
                    ),
                )
                return
            }
        }
        filterChain.doFilter(request, response)
    }
}
