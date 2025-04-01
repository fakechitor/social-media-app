package com.fakechitor.socialmediauserservice.exception

import com.fakechitor.socialmediauserservice.dto.response.ExceptionMessageDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(e: UserNotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionMessageDto(e.message))
}
