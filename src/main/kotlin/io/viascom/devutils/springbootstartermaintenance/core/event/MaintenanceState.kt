package io.viascom.devutils.springbootstartermaintenance.core.event

enum class MaintenanceState(val state: Boolean) {
    ENABLED(true), DISABLED(false)
}