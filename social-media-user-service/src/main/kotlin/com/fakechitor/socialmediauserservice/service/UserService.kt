package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.dto.mapper.UserMapper
import com.fakechitor.socialmediauserservice.dto.request.UserRegisterDto
import com.fakechitor.socialmediauserservice.exception.UserAlreadyExistsException
import com.fakechitor.socialmediauserservice.exception.UserNotFoundException
import com.fakechitor.socialmediauserservice.model.User
import com.fakechitor.socialmediauserservice.repository.UserRepository
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {
    fun save(userDto: UserRegisterDto) =
        runCatching { userRepository.save(userMapper.registerDtoToModel(userDto)) }
            .fold(
                onSuccess = { userMapper.modelToDto(it) },
                onFailure = { exception ->
                    when (exception) {
                        is ConstraintViolationException -> throw UserAlreadyExistsException("User already exists")
                        else -> throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)
                    }
                },
            )

    fun findByUsername(username: String) =
        userRepository.findByUsername(username)?.let { userMapper.modelToDto(it) } ?: throw UserNotFoundException("User not found")

    fun findById(id: Long): User = userRepository.findById(id).orElseThrow { UserNotFoundException("User with id $id not found") }
}
