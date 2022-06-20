package com.musa.approvalsys.controller

import com.musa.approvalsys.dto.user.UserDTO
import com.musa.approvalsys.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user/api")
class UserController(private val userService: UserService) {

    @GetMapping("users")
    fun users() = ok(userService.users())

    @GetMapping("user/{id}")
    fun getUserById(@PathVariable id: Long) = ok(userService.user(id))

    @GetMapping("userByEmail/{email}")
    fun getUserByMail(@PathVariable email: String) = ok(userService.user(email))

    @PostMapping("user")
    fun add(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity<UserDTO>(userService.add(user), HttpStatus.CREATED)
    }

    @PutMapping("user/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity<UserDTO>(userService.update(id, user), HttpStatus.ACCEPTED)
    }

    @DeleteMapping("user/{id}")
    fun delete(@PathVariable id: Long) = ok(userService.delete(id))
}
