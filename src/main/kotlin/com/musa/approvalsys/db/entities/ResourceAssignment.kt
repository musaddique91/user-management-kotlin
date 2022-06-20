package com.musa.approvalsys.db.entities

import com.musa.approvalsys.db.convertor.StringListConvertor
import javax.persistence.Convert
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
    @Convert(converter = StringListConvertor::class)
    var resources: List<String>
)
