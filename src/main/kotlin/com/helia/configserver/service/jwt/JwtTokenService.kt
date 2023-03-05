package com.helia.configserver.service.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.core.userdetails.UserDetails
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import java.util.function.Function

@Component
class JwtTokenService(@Value("\${jwt.secret}") private val secret: String? = null) : Serializable {

    fun getUsernameFromToken(token: String?) = getClaimFromToken(token) { obj: Claims -> obj.subject }!!


    fun getExpirationDateFromToken(token: String?) = getClaimFromToken(token) { obj: Claims -> obj.expiration }!!


    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T = claimsResolver.apply(getAllClaimsFromToken(token))

    private fun getAllClaimsFromToken(token: String?) = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body


    private fun isTokenExpired(token: String?) = getExpirationDateFromToken(token).before(Date())


    fun generateToken(userDetails: UserDetails?) = doGenerateToken(mutableMapOf(), userDetails!!.username)


    private fun doGenerateToken(claims: Map<String, Any>, subject: String) =
            Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                    .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact()!!

    fun validateToken(token: String?, userDetails: UserDetails) = (getUsernameFromToken(token) == userDetails.username && !isTokenExpired(token))

    companion object {
        private const val serialVersionUID = -2550185165626007488L
        const val JWT_TOKEN_VALIDITY = (5 * 60 * 60).toLong()
    }
}