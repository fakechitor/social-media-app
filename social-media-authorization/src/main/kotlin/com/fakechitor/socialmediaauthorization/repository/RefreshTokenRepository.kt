package com.fakechitor.socialmediaauthorization.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class RefreshTokenRepository(
    private val redisTemplate: RedisTemplate<String, UserDetails>,
) {
    fun saveToken(
        token: String,
        value: UserDetails,
    ) = redisTemplate.opsForValue().set(token, value)

    fun getUserDetailsByToken(token: String) = redisTemplate.opsForValue().get(token)
}
