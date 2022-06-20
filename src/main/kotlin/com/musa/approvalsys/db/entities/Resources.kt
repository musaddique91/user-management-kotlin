package com.musa.approvalsys.db.entities

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "resources")
class Resources(
    @Id var code: String,
    var name: String
)
