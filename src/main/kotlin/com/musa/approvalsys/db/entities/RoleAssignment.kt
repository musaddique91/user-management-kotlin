package com.musa.approvalsys.db.entities

import com.musa.approvalsys.db.convertor.StringListConvertor
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "role_assignment")
class RoleAssignment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var userId: Long,
    @Convert(converter = StringListConvertor::class)
    var roles: List<Long>
)
