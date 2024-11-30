package ru.q2l3ntk.nmapicore.services.tokens

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.security.Key
import java.util.*

internal object TokenAuthenticationService {
    private val TOKEN_EXPIRY: Long = 864000000
    private lateinit var SECRET: String
    private val TOKEN_PREFIX = "Bearer"
    private val AUTHORIZATION_HEADER_KEY = "Authorization"

    private fun getSigningKey(): Key {
        val keyBytes: ByteArray = Decoders.BASE64.decode(this.SECRET)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun addAuthentication(res: HttpServletResponse, username: String) {
        val JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_EXPIRY))
            .signWith(getSigningKey())
            .compact()

        res.addHeader(AUTHORIZATION_HEADER_KEY, "$TOKEN_PREFIX $JWT")
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(AUTHORIZATION_HEADER_KEY)

        if (token != null) {
            val user = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .body.subject

            if (user != null) {
                return UsernamePasswordAuthenticationToken(user, null, emptyList<GrantedAuthority>())
            }
        }

        return null
    }
}