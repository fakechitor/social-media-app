package com.fakechitor.socialmediauserservice.controller

import com.fakechitor.socialmediauserservice.docs.internal.GetUserByUsernameDocs
import com.fakechitor.socialmediauserservice.docs.internal.SaveUserDocs
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
    @GetUserByUsernameDocs
    fun getUserByUsername(
        @PathVariable username: String,
    ) = ResponseEntity.ok(userService.findByUsername(username))

    @PostMapping
    @SaveUserDocs
    fun saveUser(
        @RequestBody userRegisterDto: UserRegisterDto,
    ) = ResponseEntity.ok(userService.save(userRegisterDto))
}
