package io.viascom.springbootstartermaintenance.library.events

import io.viascom.springbootstartermaintenance.library.MaintenanceConfig
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


@Component
open class MaintenanceEventListener(
    private val maintenanceConfig: MaintenanceConfig
) : ApplicationListener<MaintenanceEvent> {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun onApplicationEvent(event: MaintenanceEvent) {
        log.info("Received maintenance event with action: ${event.action}")
    }
}