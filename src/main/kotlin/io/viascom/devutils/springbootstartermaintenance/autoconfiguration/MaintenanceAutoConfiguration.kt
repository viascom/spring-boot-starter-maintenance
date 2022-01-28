package io.viascom.devutils.springbootstartermaintenance.autoconfiguration

import io.viascom.devutils.springbootstartermaintenance.core.Maintenance
import io.viascom.devutils.springbootstartermaintenance.core.MaintenanceAlert
import io.viascom.devutils.springbootstartermaintenance.core.MaintenanceCleaner
import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
import io.viascom.devutils.springbootstartermaintenance.core.event.MaintenanceEventPublisher
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Maintenance autoconfiguration for spring boot projects
 *
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(WebSecurityConfigurerAdapter::class)
@EnableConfigurationProperties(MaintenanceConfigurationProperties::class)
@ComponentScan(basePackages = ["io.viascom.devutils.springbootstartermaintenance.*"])
open class MaintenanceAutoConfiguration(
    private val properties: MaintenanceConfigurationProperties,
    private val alerts: List<MaintenanceAlert>,
    private val cleaners: List<MaintenanceCleaner>,
    private val eventPublisher: MaintenanceEventPublisher
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    @Scope("singleton")
    @ConditionalOnMissingBean
    open fun maintenance(): Maintenance {
        log.debug("Initialized maintenance properties: $properties")

        if (properties.events) {
            log.debug("Maintenance events will be published.")
        }

        if (properties.enabled) {
            log.info("Maintenance mode enabled. Only users with configured roles will have access!")
        }

        return Maintenance(
            MaintenanceProperties(
                properties.enabled,
                properties.roles,
                properties.alert,
                properties.clean,
                properties.retryAfter,
                properties.events
            ),
            alerts,
            cleaners,
            eventPublisher
        )
    }
}