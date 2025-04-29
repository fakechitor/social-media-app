package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.docs.open.subscription.SubscribeDocs
import com.fakechitor.socialmediauserservice.docs.open.subscription.UnsubscribeDocs
import com.fakechitor.socialmediauserservice.service.SubscriptionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users/subscriptions")
class SubscriptionController(
    private val subscriptionService: SubscriptionService,
) {
    @PostMapping("/{id}")
    @SubscribeDocs
    fun subscribe(
        @PathVariable("id") subscribedId: Long,
        @RequestHeader("Authorization") authHeader: String,
    ) = ResponseEntity.ok(subscriptionService.subscribe(subscribedId, authHeader))

    @DeleteMapping("/{id}")
    @UnsubscribeDocs
    fun unsubscribe(
        @PathVariable("id") subscribedId: Long,
        @RequestHeader("Authorization") authHeader: String,
    ): ResponseEntity<Void> {
        subscriptionService.unsubscribe(subscribedId, authHeader)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
