package com.musa.approvalsys.services.user

import com.musa.approvalsys.db.entities.Auth
import com.musa.approvalsys.db.repositories.AuthRepository
import com.musa.approvalsys.db.repositories.UserRepository
import com.musa.approvalsys.dto.user.UserDTO
import com.musa.approvalsys.exceptions.AppSysErrorCodes
import com.musa.approvalsys.exceptions.AppSysException
import com.musa.approvalsys.mapper.UserMapper
import com.musa.approvalsys.services.MailExecutorService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder,
    private val mailService: MailExecutorService

) : IUser {

    override fun users(): List<UserDTO> {
        val users = mutableListOf<UserDTO>()
        userRepository.findAll().forEach {
            val userDTO = userMapper.mapUser(it)
            val auth = authRepository.findByIdOrNull(it.authId)
            userDTO.enable = auth!!.enable
            users.add(userDTO)
        }
        return users
    }

    override fun user(id: Long): UserDTO {
        return userRepository.findById(id).map {
            val auth = authRepository.findByIdOrNull(it.authId)
            val mapUser = userMapper.mapUser(it)
            mapUser.enable = auth!!.enable
            mapUser
        }.orElseThrow {
            AppSysException(
                AppSysErrorCodes.USER_NOT_EXIST.code, AppSysErrorCodes.USER_NOT_EXIST.message
            )
        }
    }

    override fun user(email: String): UserDTO {
        return userRepository.findByEmail(email).map {
            val userDTO = userMapper.mapUser(it)
            val auth = authRepository.findByIdOrNull(it.authId)
            userDTO.enable = auth!!.enable
            userDTO
        }.orElseThrow {
            AppSysException(
                AppSysErrorCodes.USER_NOT_EXIST.code, AppSysErrorCodes.USER_NOT_EXIST.message
            )
        }
    }

    override fun add(user: UserDTO): UserDTO {
        val byEmail = userRepository.findByEmail(user.email)
        if (byEmail.isPresent) {
            throw AppSysException(AppSysErrorCodes.USER_ALREADY_EXIST.code, AppSysErrorCodes.USER_ALREADY_EXIST.message)
        }
        val password = passwordEncoder.encode(user.tempPassword ?: "password1")
        val auth = Auth(user.id ?: 0, username = user.email, password = password, enable = true, isTempPassword = true)
        val authEntity = authRepository.save(auth)
        val userEntity = userMapper.mapUser(user, authEntity.id)
        mailService.sendWelcomeMail(user.email, user.firstName + (user.lastName ?: ""))
        return userMapper.mapUser(userRepository.save(userEntity))
    }

    override fun update(id: Long, user: UserDTO): UserDTO {
        val userEntity = userRepository.findById(id).map { userMapper.mapUserForUpdate(it, user) }
        return userMapper.mapUser(userRepository.save(userEntity.get()))
    }

    override fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    fun resetPassword(id: Long, oldPassword: String, newPassword: String): Boolean {
        authRepository.findByIdOrNull(id)?.let {
            if (!passwordEncoder.matches(oldPassword, it.password)) {
                throw AppSysException(
                    AppSysErrorCodes.PASSWORD_DOES_NOT_MATCH.code, AppSysErrorCodes.PASSWORD_DOES_NOT_MATCH.message
                )
            }
            it.password = passwordEncoder.encode(newPassword)
            authRepository.save(it)
        } ?: throw AppSysException(
            AppSysErrorCodes.USER_NOT_EXIST.code, AppSysErrorCodes.USER_NOT_EXIST.message
        )
        return true
    }

    fun resetPassword(uuid: String, oldPassword: String, newPassword: String): Boolean {
        authRepository.findByUuidToken(uuid)?.let {
            if (!passwordEncoder.matches(oldPassword, it.password)) {
                throw AppSysException(
                    AppSysErrorCodes.PASSWORD_DOES_NOT_MATCH.code, AppSysErrorCodes.PASSWORD_DOES_NOT_MATCH.message
                )
            }
            it.password = passwordEncoder.encode(newPassword)
            authRepository.save(it)
        } ?: throw AppSysException(
            AppSysErrorCodes.TOKEN_NOT_MATCH.code, AppSysErrorCodes.TOKEN_NOT_MATCH.message
        )
        return true

    }
}
