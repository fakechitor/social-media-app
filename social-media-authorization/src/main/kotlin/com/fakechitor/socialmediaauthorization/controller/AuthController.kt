package com.fakechitor.socialmediaauthorization.controller

import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.service.AuthService
import org.springframework.http.ResponseEntity
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
    ) = ResponseEntity.ok(authService.authentication(userLoginDto))

    @PostMapping("/register")
    fun register(
        @RequestBody userRegisterDto: UserRegisterDto,
    ) = ResponseEntity.ok(authService.register(userRegisterDto))
}
