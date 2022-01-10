package io.viascom.devutils.springbootstartermaintenance.core.config

data class MaintenanceProperties(
    val enabled: Boolean,
    val roles: MutableList<String>,
    val autoClean: Boolean,
    val autoAlert: Boolean
)