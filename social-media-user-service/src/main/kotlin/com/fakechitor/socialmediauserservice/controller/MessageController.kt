package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.dto.request.MessageRequestDto
import com.fakechitor.socialmediauserservice.service.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/message")
class MessageController(
    private val chatService: ChatService,
) {
    @GetMapping("/{id}")
    fun getMessages(
        @PathVariable("id") receiverId: Long,
        @RequestHeader("Authorization") authHeader: String,
    ) = ResponseEntity.ok(
        chatService.getMessages(receiverId, authHeader),
    )

    @PostMapping("/{id}")
    fun sendMessage(
        @PathVariable("id") receiverId: Long,
        @RequestBody messageRequestDto: MessageRequestDto,
        @RequestHeader("Authorization") authHeader: String,
    ) = ResponseEntity.ok(
        chatService.sendMessage(messageRequestDto, receiverId, authHeader),
    )
}
