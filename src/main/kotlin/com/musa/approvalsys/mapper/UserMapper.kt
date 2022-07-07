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
            true,
            user.createdAt!!,
            user.roleId
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
            authId,
            user.roleId
        )
    }

    fun mapUserForUpdate(dbUser: User, userDTO: UserDTO, ): User {
        dbUser.firstName = userDTO.firstName
        dbUser.lastName = userDTO.lastName
        dbUser.designation = userDTO.designation
        dbUser.phone = userDTO.phone
        dbUser.roleId = userDTO.roleId
        return dbUser
    }
}
