package io.viascom.devutils.springbootstartermaintenance.autoconfiguration

import io.viascom.devutils.springbootstartermaintenance.core.config.DefaultMaintenanceConfig
import io.viascom.devutils.springbootstartermaintenance.core.config.MaintenanceConfig
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Maintenance autoconfiguration for spring boot projects
 *
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(WebSecurityConfigurerAdapter::class)
@EnableConfigurationProperties(MaintenanceProperties::class)
@ComponentScan(basePackages = ["io.viascom.springbootstartermaintenance.*"])
open class MaintenanceAutoConfiguration(
    private val maintenanceProperties: MaintenanceProperties
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    @ConditionalOnMissingBean
    open fun maintenanceConfig(): MaintenanceConfig {
        val config = DefaultMaintenanceConfig(maintenanceProperties)
        log.info("Initialized maintenance mode with state: ${config.getEnabled()}")
        return config
    }
}