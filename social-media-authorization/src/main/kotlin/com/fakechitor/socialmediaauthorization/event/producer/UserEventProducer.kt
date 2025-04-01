package com.fakechitor.socialmediaauthorization.event.producer

import com.fakechitor.socialmediaauthorization.config.kafka.KafkaProducerConfig.Companion.USER_LOGIN_TOPIC
import com.fakechitor.socialmediaauthorization.config.kafka.KafkaProducerConfig.Companion.USER_REGISTER_TOPIC
import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class UserEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun sendRegister(userRegisterDto: UserRegisterDto) {
        kafkaTemplate.send(
            USER_REGISTER_TOPIC,
            userRegisterDto,
        )
        logger.info("User registration message sent")
    }

    fun sendLogin(userLoginDto: UserLoginDto) {
        kafkaTemplate.send(
            USER_LOGIN_TOPIC,
            userLoginDto,
        )
        logger.info("User login message sent")
    }
}
