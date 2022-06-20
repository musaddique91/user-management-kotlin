package com.musa.approvalsys.security

import com.musa.approvalsys.db.repositories.AuthRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService(private val authRepository: AuthRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = authRepository.findByUsername(username!!) ?: throw UsernameNotFoundException("user not found")
        return User(user.username, user.password, emptyList())
    }
}
