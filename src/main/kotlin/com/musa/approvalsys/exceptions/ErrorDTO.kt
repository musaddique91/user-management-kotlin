package com.musa.approvalsys.exceptions

data class ErrorDTO(val code: Int, val message: String?, val path: String, val httpStatus: Int)
