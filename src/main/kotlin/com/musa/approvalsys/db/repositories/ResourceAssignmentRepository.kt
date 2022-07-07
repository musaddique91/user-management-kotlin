package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.ResourceAssignment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying

interface ResourceAssignmentRepository : JpaRepository<ResourceAssignment, Long> {
    fun deleteByRoleId(roleId: Long): Long
    fun findByRoleId(roleId: Long): List<ResourceAssignment>
}
