package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceConfig
import io.viascom.devutils.springbootstartermaintenance.core.event.MaintenanceAction
import io.viascom.devutils.springbootstartermaintenance.core.event.MaintenanceEventPublisher
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
open class Maintenance(
    private val maintenanceConfig: MaintenanceConfig,
    private val maintenanceEventPublisher: MaintenanceEventPublisher
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun start() {
        maintenanceConfig.setEnabled(true)
        if (maintenanceConfig.getPublishEvents()) {
            maintenanceEventPublisher.publishMaintenanceEvent(
                MaintenanceAction.ENABLE
            )
        }
    }

    fun stop() {
        maintenanceConfig.setEnabled(false)
        if (maintenanceConfig.getPublishEvents()) {
            maintenanceEventPublisher.publishMaintenanceEvent(
                MaintenanceAction.DISABLE
            )
        }
    }

    fun status(): Boolean {
        return maintenanceConfig.getEnabled()
    }
}