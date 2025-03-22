package com.fakechitor.socialmediauserservice.repository

import com.fakechitor.socialmediauserservice.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}
