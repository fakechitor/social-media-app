package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.dto.mapper.UserMapper
import com.fakechitor.socialmediauserservice.model.User
import com.fakechitor.socialmediauserservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {
    fun save(user: User) = userMapper.modelToDto(user = userRepository.save(user))

    fun findByUsername(username: String) = userMapper.modelToDto(user = userRepository.findByUsername(username))
}
