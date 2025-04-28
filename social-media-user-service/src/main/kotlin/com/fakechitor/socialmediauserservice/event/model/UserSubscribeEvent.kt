package com.fakechitor.socialmediauserservice.event.model

data class UserSubscribeEvent(
    val subscriberId: Long,
    val subscribedToId: Long,
)