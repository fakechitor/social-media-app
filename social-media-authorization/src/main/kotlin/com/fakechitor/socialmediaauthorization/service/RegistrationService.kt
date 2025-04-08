package com.fakechitor.socialmediaauthorization.service

import com.fakechitor.socialmediaauthorization.dto.request.UserRegisterDto
import com.fakechitor.socialmediaauthorization.dto.response.AuthenticationResponse
import com.fakechitor.socialmediaauthorization.utils.Utils.mapToUserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val passwordEncoder: PasswordEncoder,
    private val authService: AuthService,
    private val userServiceClient: UserServiceClient,
) {
    fun register(userRegisterDto: UserRegisterDto): AuthenticationResponse {
        val encryptedPasswordDto =
            UserRegisterDto(userRegisterDto.email, userRegisterDto.login, passwordEncoder.encode(userRegisterDto.password))
        val user =
            userServiceClient.saveUser(encryptedPasswordDto).mapToUserDetails()
        return authService.generateAndSaveTokens(user = user)
    }
}
