package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.docs.open.message.GetMessagesDocs
import com.fakechitor.socialmediauserservice.docs.open.message.SendMessageDocs
import com.fakechitor.socialmediauserservice.dto.request.MessageRequestDto
import com.fakechitor.socialmediauserservice.service.ChatService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/message")
class MessageController(
    private val chatService: ChatService,
) {
    @GetMapping("/{id}")
    @GetMessagesDocs
    fun getMessages(
        @PathVariable("id") receiverId: Long,
        @RequestHeader("Authorization") authHeader: String,
    ) = ResponseEntity.ok(
        chatService.getMessages(receiverId, authHeader),
    )

    @PostMapping("/{id}")
    @SendMessageDocs
    fun sendMessage(
        @PathVariable("id") receiverId: Long,
        @Valid @RequestBody messageRequestDto: MessageRequestDto,
        @RequestHeader("Authorization") authHeader: String,
    ) = ResponseEntity.ok(
        chatService.sendMessage(messageRequestDto, receiverId, authHeader),
    )
}
