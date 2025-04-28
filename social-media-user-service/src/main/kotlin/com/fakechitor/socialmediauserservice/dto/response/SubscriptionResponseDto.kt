package com.fakechitor.socialmediauserservice.dto.response

data class SubscriptionResponseDto(
    val subscriberId: Long,
    val subscribedUserId: Long,
    val subscribedUsername: String,
)
