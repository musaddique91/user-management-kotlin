package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.Auth
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<Auth, Long> {
    fun findByUsername(username: String): Auth?
    fun findByUuidToken(token: String): Auth?
}
