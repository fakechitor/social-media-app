package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.model.Message
import com.fakechitor.socialmediauserservice.model.User
import com.fakechitor.socialmediauserservice.repository.MessageRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException

@Service
class MessageService(
    private val messageRepository: MessageRepository,
) {
    fun save(message: Message) =
        runCatching {
            messageRepository.save(message)
        }.fold(
            onSuccess = { it },
            onFailure = {
                it.printStackTrace()
                throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)
            },
        )

    fun findForUsers(
        firstUser: User,
        secondUser: User,
    ): List<Message> =
        runCatching { messageRepository.findForUsers(firstUser, secondUser) }.fold(
            onSuccess = { it },
            onFailure = {
                it.printStackTrace()
                throw HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR)
            },
        )
}
