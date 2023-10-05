package com.example.payhere.config

import com.example.payhere.domain.user.entity.User
import com.example.payhere.domain.user.entity.UserPrincipal
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory
import java.time.LocalDateTime


class WithMockCustomUserSecurityContextFactory : WithSecurityContextFactory<WithMockCustomUser> {
    override fun createSecurityContext(annotation: WithMockCustomUser): SecurityContext? {
        val securityContext = SecurityContextHolder.createEmptyContext()

        val principal = UserPrincipal(
            User(
                id = id,
                email = annotation.email,
                password = "password",
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now()
            )
        )

        val authenticationToken = UsernamePasswordAuthenticationToken(principal, token, principal.authorities)
        securityContext.authentication = authenticationToken
        return securityContext
    }

    companion object {
        const val id = 1L
        const val token = "accessToken"
    }
}