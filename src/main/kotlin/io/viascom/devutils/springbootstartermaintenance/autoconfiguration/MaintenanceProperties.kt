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
open class MaintenanceProperties {

    /**
     * Maintenance mode initial state. Default: false.
     */
    var enabled: Boolean = false

    /**
     * Roles to be allowed the access to the api during a maintenance.
     * This property is case-sensitive.
     */
    var roles: ArrayList<String> = arrayListOf()

    /**
     * Event publishing mechanism switch. Default: true.
     */
    var publishEvents: Boolean = true
}