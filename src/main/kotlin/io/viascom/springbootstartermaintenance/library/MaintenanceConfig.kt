package io.viascom.springbootstartermaintenance.library

interface MaintenanceConfig {
    fun getEnabled(): Boolean
    fun setEnabled(enabled: Boolean)
    fun getRoles(): ArrayList<String>
    fun setRoles(roles: ArrayList<String>)
    fun getEvents(): Boolean
    fun setEvents(enabled: Boolean)
}