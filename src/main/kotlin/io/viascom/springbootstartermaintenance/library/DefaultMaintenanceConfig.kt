package io.viascom.springbootstartermaintenance.library

import io.viascom.springbootstartermaintenance.autoconfigurer.MaintenanceProperties

open class DefaultMaintenanceConfig(
    maintenanceProperties: MaintenanceProperties
) : MaintenanceConfig {

    private var enabled: Boolean = maintenanceProperties.enabled
    private var roles: ArrayList<String> = maintenanceProperties.roles
    private var events: Boolean = maintenanceProperties.events

    override fun getEnabled(): Boolean = this.enabled

    override fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    override fun getRoles(): ArrayList<String> = this.roles

    override fun setRoles(roles: ArrayList<String>) {
        this.roles = roles
    }

    override fun getEvents(): Boolean = this.events

    override fun setEvents(enabled: Boolean) {
        this.events = enabled
    }
}