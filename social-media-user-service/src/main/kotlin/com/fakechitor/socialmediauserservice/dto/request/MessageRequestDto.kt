package com.fakechitor.socialmediauserservice.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class MessageRequestDto(
    @field:Size(min = 1, max = 1000, message = "Message size must be between 1 and 1000")
    @field:NotBlank(message = "Message cannot be empty")
    val message: String,
)
