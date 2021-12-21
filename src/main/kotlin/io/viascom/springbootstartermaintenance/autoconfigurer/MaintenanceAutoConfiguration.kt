package io.viascom.springbootstartermaintenance.autoconfigurer

import io.viascom.springbootstartermaintenance.library.DefaultMaintenanceConfig
import io.viascom.springbootstartermaintenance.library.MaintenanceConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

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