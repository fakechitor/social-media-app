package com.fakechitor.socialmediapostservice.exception;

import com.fakechitor.socialmediapostservice.dto.response.ExceptionMessageDto;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    ResponseEntity<ExceptionMessageDto> handleJwtException(JwtException e ){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionMessageDto(e.getMessage()));
    }
}
