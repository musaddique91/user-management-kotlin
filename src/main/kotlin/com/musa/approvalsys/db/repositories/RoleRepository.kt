package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findOneByName(name: String): Role?
}
