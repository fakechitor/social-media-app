package com.fakechitor.socialmediauserservice.event.producer

import com.fakechitor.socialmediauserservice.config.kafka.KafkaProducerConfig.Companion.SUBSCRIPTION_TOPIC
import com.fakechitor.socialmediauserservice.config.kafka.KafkaProducerConfig.Companion.UNSUBSCRIPTION_TOPIC
import com.fakechitor.socialmediauserservice.event.model.UserSubscribeEvent
import com.fakechitor.socialmediauserservice.event.model.UserUnsubscribeEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class SubscriptionProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendSubscriptionEvent(subscriptionEvent: UserSubscribeEvent) {
        kafkaTemplate
            .send(
                SUBSCRIPTION_TOPIC,
                subscriptionEvent,
            ).get()
        logger.info("Subscription event produced successfully")
    }

    fun sendUnsubscriptionEvent(unsubscriptionEvent: UserUnsubscribeEvent) {
        kafkaTemplate.send(
            UNSUBSCRIPTION_TOPIC,
            unsubscriptionEvent,
        )
        logger.info("Unsubscription event produced successfully")
    }
}
