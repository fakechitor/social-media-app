package com.fakechitor.socialmediaauthorization.exception

import com.fakechitor.socialmediaauthorization.dto.response.ExceptionMessageDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(e: UserNotFoundException): ResponseEntity<ExceptionMessageDto> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionMessageDto("User not found!"))

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(e: UserAlreadyExistsException): ResponseEntity<ExceptionMessageDto> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionMessageDto(e.message))
}
