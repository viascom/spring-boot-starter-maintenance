package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
import io.viascom.devutils.springbootstartermaintenance.core.event.MaintenanceEventPublisher
import io.viascom.devutils.springbootstartermaintenance.core.model.MaintenanceState
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now


open class Maintenance(
    private val properties: MaintenanceProperties,
    private val alerts: List<MaintenanceAlert>,
    private val cleaners: List<MaintenanceCleaner>,
    private val maintenanceEventPublisher: MaintenanceEventPublisher,
    private var state: MaintenanceState = MaintenanceState.DISABLED,
    var start: LocalDateTime? = null,
    var end: LocalDateTime? = null,
    var active: Boolean = false,
    var roles: MutableList<String> = mutableListOf(),
    var events: Boolean = false
) {

    init {
        this.active = properties.enabled
        this.roles = properties.roles
        this.events = properties.events

        if (active) {
            this.state = MaintenanceState.ENABLED
            this.start = now()
        }
    }

    private val log = LoggerFactory.getLogger(javaClass)

    @JvmOverloads
    fun start(
        startTime: LocalDateTime? = now(),
        expectedEndTime: LocalDateTime? = null,
        alert: Boolean? = false
    ) {
        this.active = true
        this.state = MaintenanceState.ENABLED

        log.info("Maintenance mode $state")

        this.start = startTime ?: now()
        expectedEndTime?.let { this.end = it }

        if (events) {
            maintenanceEventPublisher.publish(MaintenanceState.ENABLED)
        }

        if (alert == true || properties.alert) {
            alert()
        }
    }

    @JvmOverloads
    fun stop(clean: Boolean? = false) {
        this.active = false
        this.state = MaintenanceState.DISABLED

        log.info("Maintenance mode $state")

        this.end = now()

        if (events) {
            maintenanceEventPublisher.publish(MaintenanceState.DISABLED)
        }

        if (clean == true || properties.clean) {
            clean()
        }
    }

    fun state(): MaintenanceState {
        log.info("Maintenance mode $state")
        return state
    }

    @Suppress
    fun clean() = cleaners.forEach { it.clean() }

    @Suppress
    fun alert() = alerts.forEach { it.alert() }
}
