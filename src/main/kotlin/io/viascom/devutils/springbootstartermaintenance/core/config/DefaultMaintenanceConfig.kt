package io.viascom.devutils.springbootstartermaintenance.core.config

import io.viascom.devutils.springbootstartermaintenance.autoconfiguration.MaintenanceProperties
import org.slf4j.LoggerFactory

open class DefaultMaintenanceConfig(
    properties: MaintenanceProperties
) : MaintenanceConfig {

    private val log = LoggerFactory.getLogger(javaClass)

    private var enabled: Boolean = properties.enabled
    private var roles: ArrayList<String> = properties.roles
    private var publishEvents: Boolean = properties.publishEvents

    override fun getEnabled(): Boolean = this.enabled

    override fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    override fun getRoles(): ArrayList<String> = this.roles

    override fun setRoles(roles: ArrayList<String>) {
        this.roles = roles
    }

    override fun getPublishEvents(): Boolean = this.publishEvents

    override fun setPublishEvents(enabled: Boolean) {
        this.publishEvents = enabled
    }
}