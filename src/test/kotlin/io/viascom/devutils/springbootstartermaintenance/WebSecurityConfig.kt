package io.viascom.devutils.springbootstartermaintenance

import io.viascom.devutils.springbootstartermaintenance.core.DefaultMaintenanceAccessDeniedHandler
import io.viascom.devutils.springbootstartermaintenance.core.DefaultMaintenanceRequestMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var maintenanceRequestMatcher: DefaultMaintenanceRequestMatcher

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