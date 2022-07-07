package com.musa.approvalsys.services.role

import com.musa.approvalsys.db.entities.ResourceAssignment
import com.musa.approvalsys.db.entities.Resources
import com.musa.approvalsys.db.entities.Role
import com.musa.approvalsys.db.repositories.ResourceAssignmentRepository
import com.musa.approvalsys.db.repositories.ResourceRepository
import com.musa.approvalsys.db.repositories.RoleRepository
import com.musa.approvalsys.db.repositories.UserRepository
import com.musa.approvalsys.dto.user.RoleDTO
import com.musa.approvalsys.dto.user.RoleResourceDTO
import com.musa.approvalsys.exceptions.AppSysErrorCodes
import com.musa.approvalsys.exceptions.AppSysException
import com.musa.approvalsys.mapper.RoleMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleService(
    private val resourceRepository: ResourceRepository,
    private val roleRepository: RoleRepository,
    private val resourceAssignmentRepository: ResourceAssignmentRepository,
    private val roleMapper: RoleMapper,
    private val userRepository: UserRepository
) {
    fun resources(): MutableList<Resources> = resourceRepository.findAll()
    fun roles(): List<RoleDTO> {
        val roles = mutableListOf<RoleDTO>()
        roleRepository.findAll().forEach {
            val element = getRole(it)
            element?.let { e ->
                roles.add(e)
            }
        }
        return roles
    }

    @Transactional
    fun assignResources(roleId: Long, resources: List<RoleResourceDTO>): List<ResourceAssignment> {
        resourceAssignmentRepository.deleteByRoleId(roleId)
        val raList = resources.map { ResourceAssignment(0, roleId, it.resource, it.read, it.write) }
        return resourceAssignmentRepository.saveAll(raList)
    }

    @Transactional
    fun createRole(roleDTO: RoleDTO): RoleDTO {
        roleRepository.findOneByName(roleDTO.name)?.let {
            throw AppSysException(AppSysErrorCodes.ROLE_ALREADY_EXIST.code, AppSysErrorCodes.ROLE_ALREADY_EXIST.message)
        }
        val roleEntity = roleRepository.save(Role(0, roleDTO.name))
        val transform: (RoleResourceDTO) -> ResourceAssignment =
            {
                ResourceAssignment(
                    0, roleEntity.id, it.resource, it.read, it.write
                )
            }
        if (!roleDTO.resources.isNullOrEmpty()) {
            resourceAssignmentRepository.saveAll(roleDTO.resources!!.map(transform))
        }
        return roleMapper.mapRole(roleEntity)
    }

    fun assignRoles(userId: Long, roleId: Long): Boolean {
        userRepository.findByIdOrNull(userId)?.let {
            it.roleId = roleId
            userRepository.save(it)
        }
        return true
    }

    fun getRoleById(id: Long): RoleDTO? = getRole(roleRepository.findByIdOrNull(id))

    private fun getRole(roleEntity: Role?): RoleDTO? {
        return roleEntity?.let {
            val userCount = userRepository.countByRoleId(it.id)
            val resources = resourceAssignmentRepository.findByRoleId(it.id)
            val resourcesDTO = resources.map { r -> RoleResourceDTO(r.resource, r.isRead, r.isWrite) }
            RoleDTO(it.id, it.name, resourcesDTO, userCount.toInt(), it.createdBy, it.createdAt)
        }
    }

    fun deleteRole(id: Long): Long {
        val countByRoleId = userRepository.countByRoleId(id)
        if (countByRoleId > 0) {
            throw AppSysException(AppSysErrorCodes.ROLE_IS_IN_USE.code, AppSysErrorCodes.ROLE_IS_IN_USE.message)
        }
        resourceAssignmentRepository.deleteByRoleId(id)
        roleRepository.deleteById(id)
        return id
    }
}
