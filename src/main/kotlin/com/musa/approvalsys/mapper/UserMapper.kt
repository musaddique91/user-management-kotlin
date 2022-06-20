package com.musa.approvalsys.mapper

import com.musa.approvalsys.db.entities.User
import com.musa.approvalsys.dto.user.UserDTO
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun mapUser(user: User): UserDTO {
        return UserDTO(
            user.id,
            user.email,
            null,
            user.firstName,
            user.lastName,
            user.phone,
            user.designation,
            user.createdAt!!
        )
    }

    fun mapUser(user: UserDTO, authId: Long): User {
        return User(
            user.id ?: 0,
            user.email,
            user.firstName,
            user.lastName,
            user.phone,
            user.designation,
            authId
        )
    }
}
