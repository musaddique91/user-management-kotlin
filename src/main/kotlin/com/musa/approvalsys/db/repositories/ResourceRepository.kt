package com.musa.approvalsys.db.repositories

import com.musa.approvalsys.db.entities.Resources
import org.springframework.data.jpa.repository.JpaRepository

interface ResourceRepository : JpaRepository<Resources, String>
