package io.viascom.springbootstartermaintenance.library.events

enum class MaintenanceAction(val state: Boolean) {
    ENABLE(true), DISABLE(false)
}