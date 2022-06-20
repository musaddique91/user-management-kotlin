package com.musa.approvalsys.db.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "role")
class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var name: String
)
