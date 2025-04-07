package com.fakechitor.socialmediaauthorization.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RefreshTokenRepository(
    private val redisTemplate: RedisTemplate<String, String>,
) {
    fun saveToken(
        token: String,
        value: String,
    ) = redisTemplate.opsForValue().set(token, value)

    fun getUserDetailsByToken(token: String) = redisTemplate.opsForValue().get(token)
}
