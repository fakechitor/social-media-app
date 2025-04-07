package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.dto.mapper.UserMapper
import com.fakechitor.socialmediauserservice.dto.request.UserRegisterDto
import com.fakechitor.socialmediauserservice.exception.UserAlreadyExistsException
import com.fakechitor.socialmediauserservice.exception.UserNotFoundException
import com.fakechitor.socialmediauserservice.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {
    fun save(userDto: UserRegisterDto) =
        runCatching { userRepository.save(userMapper.registerDtoToModel(userDto)) }
            .fold(
                onSuccess = { userMapper.modelToDto(it) },
                onFailure = { throw UserAlreadyExistsException("User with that name already exists") },
            )

    fun findByUsername(username: String) =
        userRepository.findByUsername(username)?.let { userMapper.modelToDto(it) } ?: throw UserNotFoundException("User not found")

    fun findById(id: Long) =
        userRepository.findByIdOrNull(id)?.let { userMapper.modelToDto(it) } ?: throw UserNotFoundException("User not found")
}
