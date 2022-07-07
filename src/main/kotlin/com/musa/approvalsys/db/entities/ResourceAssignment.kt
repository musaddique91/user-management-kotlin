package com.musa.approvalsys.db.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "resource_assignment")
class ResourceAssignment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var roleId: Long,
    var resource: String,
    var isRead: Boolean,
    var isWrite: Boolean
)
