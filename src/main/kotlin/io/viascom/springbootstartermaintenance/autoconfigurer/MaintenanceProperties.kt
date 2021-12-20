package io.viascom.springbootstartermaintenance.autoconfigurer

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "maintenance")
open class MaintenanceProperties {

    /**
     * Maintenance mode initial state. Default: false.
     */
    var enabled: Boolean = false

    /**
     * Roles to be allowed the access to the api during a maintenance.
     */
    var roles: ArrayList<String> = arrayListOf()

    /**
     * Event publishing mechanism on. Default: true.
     */
    var events: Boolean = true
}