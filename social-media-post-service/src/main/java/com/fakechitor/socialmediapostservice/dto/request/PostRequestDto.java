package com.fakechitor.socialmediapostservice.dto.request;


import jakarta.validation.constraints.Size;

public record PostRequestDto(
        @Size(max = 30, message = "Title length cant be more than 30")
        String title,
        @Size(max = 1000, message = "Text length cant be more than 1000")
        String text
) {}