package com.musa.approvalsys.services.user

import com.musa.approvalsys.dto.user.UserDTO

interface IUser {
    fun users(): List<UserDTO>
    fun user(id: Long): UserDTO
    fun user(email: String): UserDTO
    fun add(user: UserDTO): UserDTO
    fun update(id: Long, user: UserDTO): UserDTO
    fun delete(id: Long)
}
