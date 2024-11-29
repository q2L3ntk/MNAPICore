package ru.q2l3ntk.nmapicore.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.web.filter.GenericFilterBean

class JWTAuthenticationFilter : GenericFilterBean() {
    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain?) {
        TODO("Not yet implemented")
    }
}