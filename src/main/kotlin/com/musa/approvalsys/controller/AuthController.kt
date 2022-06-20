package com.musa.approvalsys.controller

import com.musa.approvalsys.security.JWTUtil
import com.musa.approvalsys.security.LoginRequest
import com.musa.approvalsys.security.LoginResponse
import com.musa.approvalsys.security.UserDetailsService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import kotlin.Throws

@RestController
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
    private val userDetailsService: UserDetailsService
) {
    @PostMapping("auth/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        authenticate(loginRequest.username, loginRequest.password)
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)
        val token = jwtUtil.generateToken(userDetails)
        return ok(LoginResponse(loginRequest.username, token, jwtUtil.getExpirationFromToken(token)))
    }

    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("user disable", e)
        } catch (e: BadCredentialsException) {
            throw Exception("bad credentials", e)
        } catch (e: AuthenticationException) {
            throw Exception("bad credentials", e)
        }
    }
}
