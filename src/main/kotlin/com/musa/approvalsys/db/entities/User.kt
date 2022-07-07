package com.musa.approvalsys.db.entities

import com.quickget.backend.models.order.AuditableAttributes
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var email: String,
    var firstName: String,
    var lastName: String?,
    var phone: String?,
    var designation: String?,
    var authId: Long,
    var roleId: Long?
) : AuditableAttributes()
