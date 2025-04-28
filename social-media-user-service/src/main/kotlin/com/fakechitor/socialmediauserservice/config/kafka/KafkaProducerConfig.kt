package com.fakechitor.socialmediauserservice.config.kafka

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

@Configuration
@EnableKafka
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    companion object {
        const val SUBSCRIPTION_TOPIC = "user-subscribe-topic"
        const val UNSUBSCRIPTION_TOPIC = "user-unsubscribe-topic"
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, Any> =
        DefaultKafkaProducerFactory(
            mapOf(
                BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
                KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
                VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
                TYPE_MAPPINGS to "subscribe:com.fakechitor.socialmediauserservice.event.model.UserSubscribeEvent",
            ),
        )

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> = KafkaTemplate(producerFactory())
}
