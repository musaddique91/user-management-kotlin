package com.quickget.backend.models.order

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class AuditableUserAttributes {
    @CreatedBy
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    var modifiedBy: String? = null
        protected set
}
