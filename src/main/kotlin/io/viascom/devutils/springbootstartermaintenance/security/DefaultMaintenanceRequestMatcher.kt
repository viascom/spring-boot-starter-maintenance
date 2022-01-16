package io.viascom.devutils.springbootstartermaintenance.security

import io.viascom.devutils.springbootstartermaintenance.core.Maintenance
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

open class DefaultMaintenanceRequestMatcher(private val maintenance: Maintenance) : RequestMatcher {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun matches(request: HttpServletRequest): Boolean {
        val auth: Authentication = SecurityContextHolder.getContext().authentication

        val roles = maintenance.roles.map { "ROLE_$it" }
        if (auth.authorities.any { roles.contains(it.authority) }) {
            log.debug("Access allowed for user ${auth.name} with registered role.")
            return false
        }

        return maintenance.active
    }
}