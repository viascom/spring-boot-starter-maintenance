package io.viascom.springbootstartermaintenance

import io.viascom.springbootstartermaintenance.library.DefaultMaintenanceAccessDeniedHandler
import io.viascom.springbootstartermaintenance.library.DefaultMaintenanceRequestMatcher
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Order(10)
@Configuration
@EnableWebSecurity
open class WebSecurityConfig(
    private val maintenanceRequestMatcher: DefaultMaintenanceRequestMatcher
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("admin").password("{noop}s3cure").roles("MAINTAINER", "ADMIN")
            .and()
            .withUser("user").password("{noop}pass").roles("USER")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .requestMatchers(maintenanceRequestMatcher).denyAll()
            .anyRequest().authenticated()
            .and()
            .cors()
            .and()
            .httpBasic()
            .and()
            .exceptionHandling().accessDeniedHandler(DefaultMaintenanceAccessDeniedHandler());
    }
}