package com.example.jwt.support.config

import com.example.jwt.user.domain.Role
import org.springframework.security.test.context.support.WithSecurityContext

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = MockUserSecurityContextFactory::class)
annotation class MockUser(
    val role: Role = Role.USER
)
