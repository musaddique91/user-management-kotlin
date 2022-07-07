package com.musa.approvalsys.dto.user

import java.time.LocalDateTime

data class UserDTO(
    var id: Long?,
    var email: String,
    var tempPassword: String?,
    var firstName: String,
    var lastName: String?,
    var phone: String?,
    var designation: String?,
    var enable: Boolean,
    var createdDate: LocalDateTime?,
    var roleId: Long?
)
