package com.fakechitor.socialmediauserservice.model

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "messages", schema = "\"user\"")
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    var sender: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    var receiver: User? = null

    @Column(name = "message", nullable = false)
    var message: String? = null

    @Column(name = "sent_at", nullable = false, updatable = false, insertable = false)
    var sentAt: Timestamp? = null
}
