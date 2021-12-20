package io.viascom.springbootstartermaintenance.library

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest


@Component
open class DefaultMaintenanceRequestMatcher(
    private val maintenanceConfig: MaintenanceConfig
) : RequestMatcher {
    override fun matches(request: HttpServletRequest): Boolean {

        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val roles = maintenanceConfig.getRoles().map { "ROLE_$it" }
        if (auth.authorities.any { roles.contains(it.authority) }) {
            return false
        }

        return maintenanceConfig.getEnabled()
    }
}