package io.viascom.springbootstartermaintenance.library

import io.viascom.springbootstartermaintenance.library.events.MaintenanceAction
import io.viascom.springbootstartermaintenance.library.events.MaintenanceEventPublisher
import org.springframework.stereotype.Service

@Service
class Maintenance(
    private val maintenanceConfig: MaintenanceConfig,
    private val maintenanceEventPublisher: MaintenanceEventPublisher
) {
    fun start() {
        maintenanceConfig.setEnabled(true)
        if (maintenanceConfig.getEvents()) {
            maintenanceEventPublisher.publishMaintenanceEvent(
                MaintenanceAction.ENABLE
            )
        }
    }

    fun stop() {
        maintenanceConfig.setEnabled(false)
        if (maintenanceConfig.getEvents()) {
            maintenanceEventPublisher.publishMaintenanceEvent(
                MaintenanceAction.DISABLE
            )
        }
    }
}