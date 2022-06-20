package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.ResourceAssignment
import org.springframework.data.jpa.repository.JpaRepository

interface ResourceAssignmentRepository : JpaRepository<ResourceAssignment, Long> {
    fun findOneByRoleId(roleId: Long): ResourceAssignment?
}
