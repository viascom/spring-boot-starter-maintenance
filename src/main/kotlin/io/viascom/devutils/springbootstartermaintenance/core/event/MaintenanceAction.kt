package io.viascom.devutils.springbootstartermaintenance.core.event

enum class MaintenanceAction(val state: Boolean) {
    ENABLE(true), DISABLE(false)
}