package com.musa.approvalsys.security

import com.musa.approvalsys.constants.Constants
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestFilter(
    val userDetailsService: UserDetailsService,
    val jwtUtil: JWTUtil
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var username: String? = null
        var token: String? = null
        val authHeaderVal = request.getHeader(Constants.HEADER_AUTHORIZATION)
        if (authHeaderVal != null && authHeaderVal.startsWith("Bearer")) {
            token = authHeaderVal.substring("Bearer".length)
            username = jwtUtil.getUsernameFromToken(token)
        } else {
        }
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username)
            if (jwtUtil.validateToken(token!!, userDetails)) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, authHeaderVal, userDetails.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}
