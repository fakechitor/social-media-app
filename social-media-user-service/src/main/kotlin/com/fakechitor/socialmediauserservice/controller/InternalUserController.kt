package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.dto.request.UserRegisterDto
import com.fakechitor.socialmediauserservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/users/internal")
class InternalUserController(
    private val userService: UserService,
) {
    @GetMapping("/username/{username}")
    fun getUserByUsername(
        @PathVariable username: String,
    ) = ResponseEntity.ok(userService.findByUsername(username))

    @PostMapping
    fun saveUser(
        @RequestBody userRegisterDto: UserRegisterDto,
    ) = ResponseEntity.ok(userService.save(userRegisterDto))
}
