package io.viascom.devutils.springbootstartermaintenance.core

import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now

open class Maintenance private constructor(
    val properties: MaintenanceProperties,
    val start: LocalDateTime?,
    val end: LocalDateTime?,
    var enabled: Boolean = false
) {

    private val log = LoggerFactory.getLogger(javaClass)

    data class Builder(
        var properties: MaintenanceProperties = MaintenanceProperties.Builder().build(),
        var start: LocalDateTime = now(),
        var end: LocalDateTime? = null,
        var enabled: Boolean = false
    ) {
        fun properties(properties: MaintenanceProperties) = apply { this.properties = properties }
        fun schedule(start: LocalDateTime, end: LocalDateTime?) = apply {
            this.start = start
            this.end = end
        }
        fun enabled(enabled: Boolean) = apply { this.enabled = enabled }
        fun build() = Maintenance(properties, start, end, enabled)
    }

    fun start(): Boolean {
        enabled = true
        log.info("Maintenance mode activated.")

        return enabled
    }

    fun stop(): Boolean{
        enabled = false
        log.info("Maintenance mode deactivated.")

        return enabled
    }

    fun state(): Boolean {
        return enabled
    }
}