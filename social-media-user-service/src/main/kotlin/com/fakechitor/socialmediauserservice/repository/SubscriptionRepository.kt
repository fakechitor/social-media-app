package com.fakechitor.socialmediauserservice.repository

import com.fakechitor.socialmediauserservice.model.Subscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    @Query("select c from Subscription c where c.subscriber.id = :subscriberId and c.subscribedTo.id = :subscribedToId")
    fun findByPairOfIds(
        subscriberId: Long,
        subscribedToId: Long,
    ): Subscription?
}
