package io.viascom.devutils.springbootstartermaintenance.autoconfiguration

import io.viascom.devutils.springbootstartermaintenance.core.Maintenance
import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceProperties
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
open class MaintenanceAutoConfiguration(private val properties: MaintenanceConfigurationProperties) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    @Scope("singleton")
    @ConditionalOnMissingBean
    open fun maintenance(): Maintenance {
        log.debug("Initializing Maintenance Bean")
        return Maintenance(MaintenanceProperties(properties.enabled, properties.roles))
    }
}