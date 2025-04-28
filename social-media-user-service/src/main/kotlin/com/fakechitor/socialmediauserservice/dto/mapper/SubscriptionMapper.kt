package com.fakechitor.socialmediauserservice.dto.mapper

import com.fakechitor.socialmediauserservice.dto.response.SubscriptionResponseDto
import com.fakechitor.socialmediauserservice.event.model.UserSubscribeEvent
import com.fakechitor.socialmediauserservice.event.model.UserUnsubscribeEvent
import com.fakechitor.socialmediauserservice.model.Subscription
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SubscriptionMapper {
    @Mapping(source = "subscriber.id", target = "subscriberId")
    @Mapping(source = "subscribedTo.id", target = "subscribedToId")
    fun toSubscribeEvent(subscription: Subscription): UserSubscribeEvent

    @Mapping(source = "subscriber.id", target = "subscriberId")
    @Mapping(source = "subscribedTo.id", target = "unsubscribedToId")
    fun toUnsubscribeEvent(subscription: Subscription): UserUnsubscribeEvent

    @Mapping(source = "subscriber.id", target = "subscriberId")
    @Mapping(source = "subscribedTo.id", target = "subscribedUserId")
    @Mapping(source = "subscribedTo.username", target = "subscribedUsername")
    fun toDto(subscription: Subscription): SubscriptionResponseDto
}
