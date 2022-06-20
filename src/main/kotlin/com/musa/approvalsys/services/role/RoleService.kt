package com.musa.approvalsys.services.role

import com.musa.approvalsys.db.entities.ResourceAssignment
import com.musa.approvalsys.db.entities.Resources
import com.musa.approvalsys.db.entities.Role
import com.musa.approvalsys.db.entities.RoleAssignment
import com.musa.approvalsys.db.repositories.ResourceAssignmentRepository
import com.musa.approvalsys.db.repositories.ResourceRepository
import com.musa.approvalsys.db.repositories.RoleAssignmentRepository
import com.musa.approvalsys.db.repositories.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val resourceRepository: ResourceRepository,
    private val roleRepository: RoleRepository,
    private val roleAssignmentRepository: RoleAssignmentRepository,
    private val resourceAssignmentRepository: ResourceAssignmentRepository
) {
    fun resources(): MutableList<Resources> = resourceRepository.findAll()
    fun roles(): MutableList<Role> = roleRepository.findAll()

    fun assignResources(roleId: Long, resources: List<String>): ResourceAssignment {
        return resourceAssignmentRepository.findOneByRoleId(roleId)?.let {
            it.resources = resources
            resourceAssignmentRepository.save(it)
        } ?: resourceAssignmentRepository.save(ResourceAssignment(0, roleId, resources))
    }

    fun assignRoles(userId: Long, roles: List<Long>): RoleAssignment {
        return roleAssignmentRepository.findOneByUserId(userId)?.let {
            it.roles = roles
            roleAssignmentRepository.save(it)
        } ?: roleAssignmentRepository.save(RoleAssignment(0, userId, roles))
    }
}
