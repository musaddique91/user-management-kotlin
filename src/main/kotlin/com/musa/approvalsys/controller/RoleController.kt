package com.musa.approvalsys.controller

import com.musa.approvalsys.services.role.RoleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @PutMapping("assignResources/{roleId}")
    fun assignResource(@PathVariable roleId: Long, @RequestBody resources: List<String>) =
        ResponseEntity.ok(roleService.assignResources(roleId, resources))

    @GetMapping("roles")
    fun roles() = ResponseEntity.ok(roleService.roles())

    @PutMapping("assignRoles/{userId}")
    fun assignRole(@PathVariable userId: Long, @RequestBody roles: List<Long>) =
        ResponseEntity.ok(roleService.assignRoles(userId, roles))
}
