package com.musa.approvalsys.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.DisabledException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import javax.security.auth.login.CredentialException

@ControllerAdvice
class GlobalexceptionHandler {
    @ExceptionHandler(value = [AppSysException::class])
    fun handlerAppSysException(ex: AppSysException, request: WebRequest): ResponseEntity<ErrorDTO> {
        val errorMessage = ErrorDTO(
            ex.code,
            ex.message,
            request.contextPath,
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [DisabledException::class])
    fun handlerDisabledException(ex: DisabledException, request: WebRequest): ResponseEntity<ErrorDTO> {
        val errorMessage = ErrorDTO(
            0,
            ex.message,
            request.contextPath,
            HttpStatus.UNAUTHORIZED.value()
        )
        return ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(value = [CredentialException::class])
    fun handlerDisabledException(ex: CredentialException, request: WebRequest): ResponseEntity<ErrorDTO> {
        val errorMessage = ErrorDTO(
            0,
            ex.message,
            request.contextPath,
            HttpStatus.UNAUTHORIZED.value()
        )
        return ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(value = [AuthenticationException::class])
    fun handlerDisabledException(ex: AuthenticationException, request: WebRequest): ResponseEntity<ErrorDTO> {
        val errorMessage = ErrorDTO(
            0,
            ex.message,
            request.contextPath,
            HttpStatus.UNAUTHORIZED.value()
        )
        return ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED)
    }
}
