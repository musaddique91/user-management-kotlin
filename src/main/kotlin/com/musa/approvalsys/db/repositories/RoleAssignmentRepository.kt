package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.RoleAssignment
import org.springframework.data.jpa.repository.JpaRepository

interface RoleAssignmentRepository : JpaRepository<RoleAssignment, Long> {
    fun findOneByUserId(userId: Long): RoleAssignment?
}
