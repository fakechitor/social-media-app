package com.fakechitor.socialmediauserservice.model

import jakarta.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(unique = true, nullable = false)
    var username: String = ""

    @Column(unique = true, nullable = false)
    var email: String = ""

    @Column(nullable = false)
    var password: String = ""
}
