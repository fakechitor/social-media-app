package com.fakechitor.socialmediauserservice.repository

import com.fakechitor.socialmediauserservice.model.Subscription
import org.springframework.data.jpa.repository.JpaRepository

interface SubscriptionRepository : JpaRepository<Subscription, Long>
