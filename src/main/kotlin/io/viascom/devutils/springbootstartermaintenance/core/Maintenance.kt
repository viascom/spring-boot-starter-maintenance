package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now


open class Maintenance(
    private val properties: MaintenanceProperties,
    private val alerts: List<MaintenanceAlert>,
    private val cleaners: List<MaintenanceCleaner>,
    var start: LocalDateTime? = null,
    var end: LocalDateTime? = null,
    var active: Boolean = false,
    var roles: MutableList<String> = mutableListOf()
) {

    init {
        this.active = properties.enabled
        this.roles = properties.roles

        if (active) {
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
        log.info("Maintenance mode activated.")

        if (startTime != null) {
            this.start = startTime
        } else {
            this.start = now()
        }

        if (expectedEndTime != null) {
            this.end = expectedEndTime
        }

        if (alert == true || properties.alert) {
            alert()
        }
    }

    @JvmOverloads
    fun stop(clean: Boolean? = false) {
        this.active = false
        log.info("Maintenance mode deactivated.")

        this.end = now()

        if (clean == true || properties.clean) {
            clean()
        }
    }

    fun state(): Boolean? {
        log.info("Maintenance state is $active")
        return active
    }

    fun clean() = cleaners.forEach { it.clean() }

    fun alert() = alerts.forEach { it.alert() }
}