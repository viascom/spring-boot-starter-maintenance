package io.viascom.devutils.springbootstartermaintenance.core.model

enum class MaintenanceState {
    ENABLED, DISABLED;

    fun toBoolean(): Boolean = this == ENABLED
}
