package com.fakechitor.socialmediapostservice.exception;

import com.fakechitor.socialmediapostservice.dto.response.ExceptionMessageDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    ResponseEntity<ExceptionMessageDto> handleJwtException(JwtException e ){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionMessageDto(e.getMessage()));
    }

    @ExceptionHandler(PostNotFoundException.class)
    ResponseEntity<ExceptionMessageDto> handlePostNotFoundException(PostNotFoundException e ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionMessageDto(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ExceptionMessageDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionMessageDto(e.getMessage()));
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    ResponseEntity<ExceptionMessageDto> handleForbiddenAccessException(ForbiddenAccessException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionMessageDto(e.getMessage()));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    ResponseEntity<ExceptionMessageDto> handleHttpServerErrorException(HttpServerErrorException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionMessageDto("Something went wrong =("));
    }
}
