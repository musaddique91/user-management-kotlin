package com.musa.approvalsys.dto.user

data class ResetPassword(val id: Long?, val uuidToken: Long?, val oldPassword: String, val newPassword: String)
