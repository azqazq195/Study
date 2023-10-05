package com.example.payhere.common.config

import com.example.payhere.domain.user.entity.User
import com.example.payhere.domain.user.entity.UserPrincipal
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*


@Configuration
@EnableJpaAuditing
class AuditingConfig {

    @Bean
    fun auditorProvider(): AuditorAware<User> {
        return AuditorAwareImpl()
    }

    class AuditorAwareImpl(
    ) : AuditorAware<User> {
        override fun getCurrentAuditor(): Optional<User> {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication == null || !authentication.isAuthenticated || authentication.principal == "anonymousUser") {
                return Optional.empty()
            }
            val userPrincipal = authentication.principal as UserPrincipal
            return Optional.of(userPrincipal.user)
        }
    }
}