package com.musa.approvalsys.config

import com.musa.approvalsys.security.SecurityHelper
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional

@Component("appsysAware")
class UserAuditAware() : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of(
            SecurityHelper.getUsername()
        )
    }
}
