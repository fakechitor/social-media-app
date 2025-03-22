package com.fakechitor.socialmediaauthorization.config.kafka

import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.kafka.support.serializer.JsonSerializer.TYPE_MAPPINGS

@EnableKafka
@Configuration
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    companion object {
        const val USER_REGISTER_TOPIC = "user-register-topic"
        const val USER_LOGIN_TOPIC = "user-login-topic"
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> =
        DefaultKafkaProducerFactory(
            mapOf(
                BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
                KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
                TYPE_MAPPINGS to
                    "login:com.fakechitor.socialmediaauthorization.dto.UserLoginDto, register:com.fakechitor.socialmediaauthorization.dto.UserRegisterDto",
            ),
        )

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> = KafkaTemplate(producerFactory())
}
