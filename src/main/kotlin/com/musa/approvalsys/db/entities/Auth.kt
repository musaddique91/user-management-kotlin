package com.musa.approvalsys.db.entities

import com.quickget.backend.models.order.AuditableDateAttributes
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "auth")
class Auth(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var username: String,
    var password: String,
    var enable: Boolean,
    var isTempPassword: Boolean,
    var uuidToken: String? = null,
) : AuditableDateAttributes()
