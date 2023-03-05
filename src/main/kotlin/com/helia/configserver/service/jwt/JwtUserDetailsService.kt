package com.helia.configserver.service.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import kotlin.Throws
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.util.ArrayList

@Service
class JwtUserDetailsService(
        @Value("\${config.userName}") private val user: String? = null,
        @Value("\${config.password}") private val pass: String? = null
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return if (user == username) {
            User(user, "{noop}$pass", ArrayList())
        } else {
            throw UsernameNotFoundException("User not found with username: $username")
        }
    }
}