package com.musa.approvalsys.security

import java.util.Date

data class LoginResponse(var username: String, var token: String, var expireOn: Date)
