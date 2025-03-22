package com.fakechitor.socialmediauserservice.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES
import org.springframework.kafka.support.serializer.JsonDeserializer.TYPE_MAPPINGS

@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    companion object {
        const val USER_REGISTER_TOPIC = "user-register-topic"
        const val USER_LOGIN_TOPIC = "user-login-topic"
        const val USER_GROUP = "user-service-group"
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> =
        DefaultKafkaConsumerFactory(
            mapOf(
                BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
                GROUP_ID_CONFIG to USER_GROUP,
                KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
                VALUE_DESERIALIZER_CLASS_CONFIG to JsonDeserializer::class.java,
                TYPE_MAPPINGS to
                    "login:com.fakechitor.socialmediauserservice.dto.request.UserLoginDto , register:com.fakechitor.socialmediauserservice.dto.request.UserRegisterDto",
                TRUSTED_PACKAGES to
                    arrayOf(
                        "com.fakechitor.socialmediaauthorization.dto.UserLoginDto",
                        "com.fakechitor.socialmediaauthorization.dto.UserRegisterDto",
                    ),
            ),
        )

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory =
            ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}
