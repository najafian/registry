package com.helia.configserver.configuration

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.beans.factory.annotation.Autowired
import com.helia.configserver.configuration.JwtRequestFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class JWTConfigurer : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    @Autowired
    private val jwtRequestFilter: JwtRequestFilter? = null
    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}