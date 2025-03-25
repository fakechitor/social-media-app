package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping
    fun getUsers() = ResponseEntity.ok(userService.findAll())

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long,
    ) = ResponseEntity.ok(userService.findById(id))

    @GetMapping("/{username}")
    fun getUserByUsername(
        @PathVariable username: String,
    ) = ResponseEntity.ok(userService.findByUsername(username))
}
