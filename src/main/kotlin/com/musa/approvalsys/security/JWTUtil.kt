package com.musa.approvalsys.security

import com.musa.approvalsys.config.ApprovalProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Component
class JWTUtil(
    private val approvalProperties: ApprovalProperties
) : Serializable {

    fun generateToken(userDetails: UserDetails): String = doGenerateToken(mutableMapOf(), userDetails.username)
    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun getUsernameFromToken(token: String): String = getAllClaimsFromToken(token).subject
    fun getExpirationFromToken(token: String): Date = getAllClaimsFromToken(token).expiration
    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(approvalProperties.jwtSecret)
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String) = getExpirationFromToken(token).before(Date())

    private fun doGenerateToken(claim: Map<String, Any>, subject: String): String {
        val validity = LocalDateTime.now().plusHours(approvalProperties.jwtTokenValidity)
        return Jwts.builder()
            .setClaims(claim).setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date.from(validity.atZone(ZoneId.systemDefault()).toInstant()))
            .signWith(SignatureAlgorithm.HS512, approvalProperties.jwtSecret).compact()
    }
}
