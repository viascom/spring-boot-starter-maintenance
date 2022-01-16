package io.viascom.devutils.springbootstartermaintenance.core.event

import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class MaintenanceEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun publishMaintenanceEvent(state: MaintenanceState) {
        log.debug("Publishing maintenance event with state $state.")
        val maintenanceEvent = MaintenanceEvent(this, state)
        applicationEventPublisher.publishEvent(maintenanceEvent)
    }
}