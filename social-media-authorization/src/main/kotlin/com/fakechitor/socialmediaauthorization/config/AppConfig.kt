package com.fakechitor.socialmediaauthorization.config

import com.fakechitor.socialmediaauthorization.property.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
class AppConfig {
    @Bean
    fun webClient(): WebClient = WebClient.create()
}
