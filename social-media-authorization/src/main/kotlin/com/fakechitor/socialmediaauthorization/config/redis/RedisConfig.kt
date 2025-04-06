package com.fakechitor.socialmediaauthorization.config.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.security.core.userdetails.UserDetails

@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory?): RedisTemplate<String, UserDetails> {
        val serializer = Jackson2JsonRedisSerializer(UserDetails::class.java)
        return RedisTemplate<String, UserDetails>().apply {
            setConnectionFactory(connectionFactory)
            setDefaultSerializer(serializer)
            keySerializer = StringRedisSerializer()
            afterPropertiesSet()
        }
    }
}
