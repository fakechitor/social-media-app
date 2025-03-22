package com.fakechitor.socialmediauserservice.event.consumer

import com.fakechitor.socialmediauserservice.config.kafka.KafkaConsumerConfig.Companion.USER_GROUP
import com.fakechitor.socialmediauserservice.config.kafka.KafkaConsumerConfig.Companion.USER_LOGIN_TOPIC
import com.fakechitor.socialmediauserservice.config.kafka.KafkaConsumerConfig.Companion.USER_REGISTER_TOPIC
import com.fakechitor.socialmediauserservice.dto.request.UserLoginDto
import com.fakechitor.socialmediauserservice.dto.request.UserRegisterDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UserEventConsumer {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = [USER_REGISTER_TOPIC], groupId = USER_GROUP, containerFactory = "kafkaListenerContainerFactory")
    fun handleRegisterEvent(userRegisterDto: UserRegisterDto) {
        logger.info("User register listener got user with login: ${userRegisterDto.login}")
        // some logic
        logger.info("User register event consumed successfully")
    }

    @KafkaListener(topics = [USER_LOGIN_TOPIC], groupId = USER_GROUP, containerFactory = "kafkaListenerContainerFactory")
    fun handleLoginEvent(userLoginDto: UserLoginDto) {
        logger.info("User login event listener user with login: ${userLoginDto.login}")
        // some logic
        logger.info("User login event consumed successfully")
    }
}
