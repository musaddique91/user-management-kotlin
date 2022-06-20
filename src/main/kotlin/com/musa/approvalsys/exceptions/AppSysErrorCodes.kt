package com.musa.approvalsys.exceptions

enum class AppSysErrorCodes(val code: Int, val message: String) {
    USER_NOT_EXIST(101, "user does not exist")
}
