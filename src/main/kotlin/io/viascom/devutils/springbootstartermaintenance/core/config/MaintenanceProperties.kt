package io.viascom.devutils.springbootstartermaintenance.core.config

open class MaintenanceProperties(
    val enabled: Boolean,
    val roles: MutableList<String>
) {
    data class Builder(
        var enabled: Boolean = false,
        var roles: MutableList<String> = mutableListOf()
    ) {
        fun enabled(enabled: Boolean) = apply { this.enabled = enabled }
        fun roles(roles: MutableList<String>) = apply { this.roles = roles }
        fun build() = MaintenanceProperties(enabled, roles)
    }
}