package io.viascom.devutils.springbootstartermaintenance.core.event

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component


@Component
open class MaintenanceEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun publishMaintenanceEvent(action: MaintenanceAction) {
        log.info("Publishing maintenance event with action $action")

        val maintenanceEvent = MaintenanceEvent(this, action)
        applicationEventPublisher.publishEvent(maintenanceEvent)
    }
}