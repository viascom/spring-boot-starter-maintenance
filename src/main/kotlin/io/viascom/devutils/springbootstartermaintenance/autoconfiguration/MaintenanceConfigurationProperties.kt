package io.viascom.devutils.springbootstartermaintenance.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 *  Maintenance configuration properties
 *
 *  This class contains all the available properties with descriptions to configure this
 *  maintenance mode library. Registering itself under the keyword "maintenance". All properties
 *  can be set in the application configuration files.
 */
@ConfigurationProperties(prefix = "maintenance")
open class MaintenanceConfigurationProperties {

    /**
     * Maintenance mode initial state. Default: false.
     */
    var enabled: Boolean = false

    /**
     * Roles to be allowed the access to the api during a maintenance.
     * This property is case-sensitive.
     */
    var roles: MutableList<String> = arrayListOf()

    /**
     * If set to true, all classes implementing the MaintenanceCleaner
     * interface will be run during the stop maintenance process.
     */
    var clean: Boolean = false

    /**
     * If set to true, all classes implementing the MaintenanceAlert
     * interface will be run during the start maintenance process.
     */
    var alert: Boolean = false

    /**
     * If set to true, spring events for enabling and disabling the
     * maintenance mode will be published.
     */
    var events: Boolean = false
}