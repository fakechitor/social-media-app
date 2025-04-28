package com.fakechitor.socialmediauserservice.event.model

data class UserUnsubscribeEvent(
    val subscriberId: Long,
    val unsubscribedToId: Long,
)
