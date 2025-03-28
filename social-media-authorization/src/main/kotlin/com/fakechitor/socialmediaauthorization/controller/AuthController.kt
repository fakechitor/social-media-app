package com.fakechitor.socialmediaauthorization.controller

import com.fakechitor.socialmediaauthorization.dto.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.UserRegisterDto
import com.fakechitor.socialmediaauthorization.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/login")
    fun authorize(
        @RequestBody userLoginDto: UserLoginDto,
    ) = authService.loginUser(userLoginDto)

    @PostMapping("/register")
    fun register(
        @RequestBody userRegisterDto: UserRegisterDto,
    ) = authService.registerUser(userRegisterDto)
}
