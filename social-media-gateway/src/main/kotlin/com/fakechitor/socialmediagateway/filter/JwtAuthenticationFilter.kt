package com.fakechitor.socialmediagateway.filter

import com.fakechitor.socialmediagateway.client.AuthClient
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {
    private lateinit var authClient: AuthClient

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = request.getJwtToken()
        if (token != null) {
            authClient.validateToken(token)
        }

        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getJwtToken(): String? {
        val header = this.getHeader(AUTHORIZATION)
        return when (header.startsWith("Bearer ")) {
            true -> header.substringAfter("Bearer ")
            false -> null
        }
    }
}
