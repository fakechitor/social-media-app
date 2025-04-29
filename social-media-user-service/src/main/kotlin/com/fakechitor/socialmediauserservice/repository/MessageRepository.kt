package com.fakechitor.socialmediauserservice.repository

import com.fakechitor.socialmediauserservice.model.Message
import com.fakechitor.socialmediauserservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MessageRepository : JpaRepository<Message, Long> {
    @Query(
        "select m from Message m where m.sender = :firstUser and m.receiver = :secondUser or m.sender = :secondUser and m.receiver = :firstUser",
    )
    fun findForUsers(
        firstUser: User,
        secondUser: User,
    ): List<Message>
}
