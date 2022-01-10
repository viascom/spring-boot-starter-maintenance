package io.viascom.devutils.springbootstartermaintenance.core

import io.github.classgraph.ClassGraph
import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.LocalDateTime.now


open class Maintenance(
    private var properties: MaintenanceProperties,
    var start: LocalDateTime = now(),
    var end: LocalDateTime? = null,
    var active: Boolean = false,
    var roles: MutableList<String> = mutableListOf()
) {

    init {
        active = properties.enabled
        roles = properties.roles
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

        if (alert == true || properties.autoAlert) {
            alert()
        }
    }

    @JvmOverloads
    fun stop(clean: Boolean? = false) {
        this.active = false
        log.info("Maintenance mode deactivated.")

        this.end = now()

        if (clean == true || properties.autoClean) {
            clean()
        }
    }

    fun state(): Boolean {
        log.info("Maintenance state is $active")
        return active
    }

    fun clean() {
        ClassGraph().enableAllInfo()
            .scan().use { scanResult ->
                val cleanerClasses = scanResult.getClassesImplementing("io.viascom.devutils.springbootstartermaintenance.core.MaintenanceCleaner")
                val cleanerClassNames = cleanerClasses.names
                log.debug(cleanerClassNames.joinToString())

                cleanerClassNames.forEach {
                    val instance: MaintenanceCleaner = Class.forName(it).getDeclaredConstructor().newInstance() as MaintenanceCleaner
                    instance.clean()
                }
            }
    }

    fun alert() {
        ClassGraph().enableAllInfo()
            .scan().use { scanResult ->
                val alertClasses = scanResult.getClassesImplementing("io.viascom.devutils.springbootstartermaintenance.core.MaintenanceAlert")
                val alertClassNames = alertClasses.names
                log.debug(alertClassNames.joinToString())

                alertClassNames.forEach {
                    val instance: MaintenanceAlert = Class.forName(it).getDeclaredConstructor().newInstance() as MaintenanceAlert
                    instance.alert()
                }
            }
    }
}