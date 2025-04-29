package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.dto.mapper.MessageMapper
import com.fakechitor.socialmediauserservice.dto.request.MessageRequestDto
import com.fakechitor.socialmediauserservice.dto.response.MessageResponseDto
import com.fakechitor.socialmediauserservice.model.Message
import com.fakechitor.socialmediauserservice.util.JwtUtils
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val messageService: MessageService,
    private val jwtUtils: JwtUtils,
    private val userService: UserService,
    private val friendService: FriendService,
    private val messageMapper: MessageMapper,
    private val entityManager: EntityManager,
) {
    @Transactional
    fun sendMessage(
        messageRequestDto: MessageRequestDto,
        receiverId: Long,
        authHeader: String,
    ): MessageResponseDto {
        val senderId = jwtUtils.getUserId(authHeader)
        friendService.throwIfNotFriends(senderId, receiverId)
        val message =
            Message().apply {
                sender = userService.findById(senderId)
                receiver = userService.findById(receiverId)
                message = messageRequestDto.message
            }
        messageService.save(message)
        entityManager.refresh(message)
        return messageMapper.toResponseDto(message)
    }

    fun getMessages(
        receiverId: Long,
        authHeader: String,
    ): List<MessageResponseDto> {
        val senderId = jwtUtils.getUserId(authHeader)
        val messages =
            messageService
                .findForUsers(
                    firstUser = userService.findById(senderId),
                    secondUser = userService.findById(receiverId),
                ).map { messageMapper.toResponseDto(it) }
        return messages
    }
}
