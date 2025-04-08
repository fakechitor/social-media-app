package com.fakechitor.socialmediaauthorization.controller

import com.fakechitor.socialmediaauthorization.docs.auth.AuthenticateUser
import com.fakechitor.socialmediaauthorization.docs.auth.RegisterUser
import com.fakechitor.socialmediaauthorization.dto.request.UserLoginDto
import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.service.AuthService
import com.fakechitor.socialmediaauthorization.service.RegistrationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/auth")
class AuthController(
    private val authService: AuthService,
    private val registrationService: RegistrationService,
) {
    @PostMapping("/login")
    @RegisterUser
    fun authorize(
        @RequestBody userLoginDto: UserLoginDto,
    ) = ResponseEntity.ok(authService.authentication(userLoginDto))

    @PostMapping("/register")
    @AuthenticateUser
    fun register(
        @RequestBody userRegisterDto: UserRegisterDto,
    ) = ResponseEntity.ok(registrationService.register(userRegisterDto))
}
