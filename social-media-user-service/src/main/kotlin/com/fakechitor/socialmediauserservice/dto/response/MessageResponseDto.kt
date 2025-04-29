package com.fakechitor.socialmediauserservice.dto.response

import java.sql.Timestamp

data class MessageResponseDto(
    val id: Long,
    val sender: String,
    val message: String,
    val sentAt: Timestamp?,
)
