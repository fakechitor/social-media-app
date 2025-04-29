package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.docs.open.friend.GetFriendshipStatusDocs
import com.fakechitor.socialmediauserservice.service.FriendService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/friends")
class FriendController(
    private val friendService: FriendService,
) {
    @GetMapping("/{id}")
    @GetFriendshipStatusDocs
    fun getFriendshipStatus(
        @PathVariable("id") id: Long,
        @RequestHeader("Authorization") authToken: String,
    ) = ResponseEntity.ok(friendService.getStatus(id, authToken))
}
