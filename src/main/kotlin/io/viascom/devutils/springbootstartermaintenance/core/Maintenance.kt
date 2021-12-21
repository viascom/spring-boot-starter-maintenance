package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceConfig
import io.viascom.devutils.springbootstartermaintenance.core.event.MaintenanceAction
import io.viascom.devutils.springbootstartermaintenance.core.event.MaintenanceEventPublisher
import org.springframework.stereotype.Service

@Service
open class Maintenance(
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