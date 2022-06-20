package com.musa.approvalsys.exceptions

class AppSysException(val code: Int, override val message: String?) : RuntimeException()
