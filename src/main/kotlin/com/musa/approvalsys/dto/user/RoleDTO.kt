package com.musa.approvalsys.dto.user

import java.time.LocalDateTime

open class RoleDTO(
    var id: Long?,
    var name: String,
    var resources: List<RoleResourceDTO>?,
    var users: Int?,
    var createdBy: String?,
    var createdDate: LocalDateTime?
)
