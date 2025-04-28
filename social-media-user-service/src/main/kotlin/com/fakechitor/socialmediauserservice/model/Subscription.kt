package com.fakechitor.socialmediauserservice.model

import jakarta.persistence.*

@Entity
@Table(name = "subscriptions", schema = "user", uniqueConstraints = [UniqueConstraint(columnNames = ["subscriber_id", "subscribed_to_id"])])
class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id")
    var subscriber: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribed_to_id")
    var subscribedTo: User? = null
}
