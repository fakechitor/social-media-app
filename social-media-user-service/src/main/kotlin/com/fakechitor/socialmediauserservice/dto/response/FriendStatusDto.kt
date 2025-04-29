package com.fakechitor.socialmediauserservice.dto.response

import com.fakechitor.socialmediauserservice.util.FriendshipStatus

data class FriendStatusDto(
    val requestedUserId: Long,
    val status: FriendshipStatus,
)
