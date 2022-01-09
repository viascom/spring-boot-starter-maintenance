package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now

open class Maintenance(
    val properties: MaintenanceProperties,
    var start: LocalDateTime = now(),
    var end: LocalDateTime? = null,
    var enabled: Boolean = false
) {

    init {
        enabled = properties.enabled
    }

    private val log = LoggerFactory.getLogger(javaClass)

    fun start(): Boolean {
        enabled = true
        log.info("Maintenance mode activated.")

        return enabled
    }

    fun stop(): Boolean {
        enabled = false
        log.info("Maintenance mode deactivated.")

        return enabled
    }

    fun state(): Boolean {
        return enabled
    }
}