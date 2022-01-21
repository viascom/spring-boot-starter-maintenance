package io.viascom.devutils.springbootstartermaintenance.autoconfiguration

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

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
     * Enable to instantiate the maintenance mode as active.
     */
    var enabled: Boolean = false

    /**
     * Roles to be allowed the access to the api during a maintenance.
     * This property is case-sensitive.
     */
    var roles: MutableList<String> = arrayListOf()

    /**
     * Enable to run all classes implementing the MaintenanceCleaner interface during the stop maintenance process.
     */
    var clean: Boolean = false

    /**
     * Enable to run all classes implementing the MaintenanceAlert interface during the start maintenance process.
     */
    var alert: Boolean = false

    /**
     * Enable to publish spring events for enabling and disabling the maintenance mode.
     */
    var events: Boolean = false

    /**
     * Default value for the "Retry-After" response HTTP header in seconds,
     * which is used in the DefaultMaintenanceAccessDeniedHandler.
     */
    var retryAfter: Int = 60
}