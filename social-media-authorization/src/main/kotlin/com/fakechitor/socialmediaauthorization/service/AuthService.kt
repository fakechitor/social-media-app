package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.UserRegisterDto
import com.fakechitor.socialmediaauthorization.event.producer.UserEventProducer
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userEventProducer: UserEventProducer,
) {
    fun loginUser(userLoginDto: UserLoginDto) = userEventProducer.sendLogin(userLoginDto)

    fun registerUser(userRegisterDto: UserRegisterDto) = userEventProducer.sendRegister(userRegisterDto)
}
