package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceConfig
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

open class DefaultMaintenanceRequestMatcher(
    private val config: MaintenanceConfig
) : RequestMatcher {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun matches(request: HttpServletRequest): Boolean {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val roles = config.getRoles().map { "ROLE_$it" }
        if (auth.authorities.any { roles.contains(it.authority) }) {
            return false
        }

        return config.getEnabled()
    }
}