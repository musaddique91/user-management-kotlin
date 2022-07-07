package com.musa.approvalsys.controller

import com.musa.approvalsys.dto.user.RoleDTO
import com.musa.approvalsys.dto.user.RoleResourceDTO
import com.musa.approvalsys.services.role.RoleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/role/api")
class RoleController(
    private val roleService: RoleService
) {
    @GetMapping("resources")
    fun resources() = ResponseEntity.ok(roleService.resources())

    @PutMapping("role/{roleId}")
    fun updateRole(@PathVariable roleId: Long, @RequestBody resources: List<RoleResourceDTO>) =
        ResponseEntity.ok(roleService.assignResources(roleId, resources))

    @PostMapping("role")
    fun addRole(@RequestBody role: RoleDTO) =
        ResponseEntity.ok(roleService.createRole(role))

    @GetMapping("role/{id}")
    fun role(@PathVariable id: Long) = ResponseEntity.ok(roleService.getRoleById(id))

    @GetMapping("roles")
    fun roles() = ResponseEntity.ok(roleService.roles())

    @PutMapping("assignRoles/{userId}/{roleId}")
    fun assignRole(@PathVariable userId: Long, @PathVariable roleId: Long,) =
        ResponseEntity.ok(roleService.assignRoles(userId, roleId))

    @DeleteMapping("role/{id}")
    fun deleteRole(@PathVariable id: Long) = ResponseEntity.ok(roleService.deleteRole(id))
}
