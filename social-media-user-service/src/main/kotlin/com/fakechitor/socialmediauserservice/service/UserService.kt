package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.dto.mapper.UserMapper
import com.fakechitor.socialmediauserservice.dto.response.UserResponseDto
import com.fakechitor.socialmediauserservice.exception.UserNotFoundException
import com.fakechitor.socialmediauserservice.model.User
import com.fakechitor.socialmediauserservice.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {
    fun save(user: User) = userMapper.modelToDto(user = userRepository.save(user))

    fun findByUsername(username: String) =
        userRepository.findByUsername(username)?.let { userMapper.modelToDto(it) } ?: throw UserNotFoundException("User not found")

    fun findById(id: Long) =
        userRepository.findByIdOrNull(id)?.let { userMapper.modelToDto(it) } ?: throw UserNotFoundException("User not found")

    fun findAll(): List<UserResponseDto> = userRepository.findAll().map { userMapper.modelToDto(it) }
}
