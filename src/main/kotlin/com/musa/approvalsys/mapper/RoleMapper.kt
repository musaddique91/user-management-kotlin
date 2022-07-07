package com.musa.approvalsys.mapper

import com.musa.approvalsys.db.entities.Role
import com.musa.approvalsys.dto.user.RoleDTO
import org.springframework.stereotype.Component

@Component
class RoleMapper {
    fun mapRole(roleDTO: RoleDTO): Role = Role(0, roleDTO.name)
    fun mapRole(role: Role): RoleDTO {
        return RoleDTO(role.id, role.name, null, 0, role.createdBy, role.updatedAt)
    }
}
