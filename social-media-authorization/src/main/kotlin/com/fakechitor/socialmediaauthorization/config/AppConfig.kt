package com.fakechitor.socialmediaauthorization.config

import com.fakechitor.socialmediaauthorization.property.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
class AppConfig
