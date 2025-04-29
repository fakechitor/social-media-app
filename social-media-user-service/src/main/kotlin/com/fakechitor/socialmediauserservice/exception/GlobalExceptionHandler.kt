package com.fakechitor.socialmediauserservice.exception

import com.fakechitor.socialmediauserservice.dto.response.ExceptionMessageDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpServerErrorException

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(e: UserNotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionMessageDto(e.message))

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(e: UserAlreadyExistsException) =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionMessageDto(e.message))

    @ExceptionHandler(HttpServerErrorException::class)
    fun handleHttpServerErrorException(e: HttpServerErrorException?) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionMessageDto("Something went wrong =("))

    @ExceptionHandler(SubscriptionNotFoundException::class)
    fun handleSubscriptionNotFoundException(e: SubscriptionNotFoundException) =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionMessageDto(e.message))

    @ExceptionHandler(UserAlreadySubscribedException::class)
    fun handleUserAlreadySubscribedException(e: UserAlreadySubscribedException) =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionMessageDto(e.message))

    @ExceptionHandler(UserNotFriendException::class)
    fun handleUserNotFriendException(e: UserNotFriendException) =
        ResponseEntity.status(HttpStatus.FORBIDDEN).body(ExceptionMessageDto(e.message))
}
