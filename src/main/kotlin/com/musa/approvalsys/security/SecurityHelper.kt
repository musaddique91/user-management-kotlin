package com.musa.approvalsys.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails

class SecurityHelper {
    companion object {
        fun getUsername(): String {
            return try {
                val authentication =
                    SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
                (authentication.principal as UserDetails).username
            } catch (e: Exception) {
                "System"
            }
        }

        fun getToken(): String {
            val authentication =
                SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
            return authentication.credentials as String
        }
    }
}
