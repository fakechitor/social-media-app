package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.dto.mapper.SubscriptionMapper
import com.fakechitor.socialmediauserservice.dto.response.SubscriptionResponseDto
import com.fakechitor.socialmediauserservice.event.producer.SubscriptionProducer
import com.fakechitor.socialmediauserservice.exception.SubscriptionNotFoundException
import com.fakechitor.socialmediauserservice.exception.UserAlreadySubscribedException
import com.fakechitor.socialmediauserservice.exception.UserNotFriendException
import com.fakechitor.socialmediauserservice.model.Subscription
import com.fakechitor.socialmediauserservice.repository.SubscriptionRepository
import com.fakechitor.socialmediauserservice.util.JwtUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SubscriptionService(
    private val jwtUtils: JwtUtils,
    private val userService: UserService,
    private val subscriptionRepository: SubscriptionRepository,
    private val subscriptionProducer: SubscriptionProducer,
    private val subscriptionMapper: SubscriptionMapper,
) {
    @Transactional
    fun subscribe(
        subscribedId: Long,
        authHeader: String,
    ): SubscriptionResponseDto {
        val subscriberId = jwtUtils.getUserId(authHeader).also { it.throwIfIdsEqual(subscribedId) }

        val subscription =
            Subscription().apply {
                this.subscriber = userService.findById(subscriberId)
                this.subscribedTo = userService.findById(subscribedId)
            }
        save(subscription)
        subscriptionProducer.sendSubscriptionEvent(subscriptionMapper.toSubscribeEvent(subscription))
        return subscriptionMapper.toDto(subscription)
    }

    private fun Long.throwIfIdsEqual(secondId: Long) {
        if (this == secondId) {
            throw UserNotFriendException("You can not subscribe yourself")
        }
    }

    private fun save(subscription: Subscription) =
        runCatching { subscriptionRepository.save(subscription) }.fold(
            onSuccess = { it },
            onFailure = { throw UserAlreadySubscribedException("You already subscribed to that user") },
        )

    @Transactional
    fun unsubscribe(
        subscribedId: Long,
        authHeader: String,
    ) {
        val subscriberId = jwtUtils.getUserId(authHeader)
        val subscription =
            subscriptionRepository.findByPairOfIds(subscriberId = subscriberId, subscribedToId = subscribedId)
                ?: throw SubscriptionNotFoundException("You are not subscribed to that user")
        subscriptionRepository.delete(subscription)
        subscriptionProducer.sendUnsubscriptionEvent(subscriptionMapper.toUnsubscribeEvent(subscription))
    }
}
