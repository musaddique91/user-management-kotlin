package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
    fun countByRoleId(roleId: Long): Long
}
