package com.fakechitor.socialmediauserservice.service

import com.fakechitor.socialmediauserservice.exception.UserNotFriendException
import com.fakechitor.socialmediauserservice.repository.SubscriptionRepository
import com.fakechitor.socialmediauserservice.util.FriendshipStatus
import org.springframework.stereotype.Service

@Service
class FriendService(
    private val subscriptionRepository: SubscriptionRepository,
) {
    fun throwIfNotFriends(
        firstUserId: Long,
        secondUserId: Long,
    ) {
        when (getFriendshipStatus(firstUserId, secondUserId)) {
            FriendshipStatus.FRIEND -> return
            else -> throw UserNotFriendException("That user is not in your friend list")
        }
    }

    fun getFriendshipStatus(
        firstUserId: Long,
        secondUserId: Long,
    ): FriendshipStatus {
        if (firstUserId == secondUserId) throw UserNotFriendException("You can not send messages to yourself")
        val sub1 = subscriptionRepository.findByPairOfIds(firstUserId, secondUserId)
        val sub2 = subscriptionRepository.findByPairOfIds(secondUserId, firstUserId)
        return if (sub1 != null && sub2 != null) {
            FriendshipStatus.FRIEND
        } else if (sub1 != null) {
            FriendshipStatus.SUBSCRIBER
        } else if (sub2 != null) {
            FriendshipStatus.PENDING
        } else {
            FriendshipStatus.NONE
        }
    }
}
