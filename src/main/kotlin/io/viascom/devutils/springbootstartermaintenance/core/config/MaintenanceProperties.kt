package io.viascom.devutils.springbootstartermaintenance.core.config

data class MaintenanceProperties(
    val enabled: Boolean,
    val roles: MutableList<String>,
    val clean: Boolean,
    val alert: Boolean,
    val events: Boolean
)