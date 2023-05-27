package com.example.springbootkotlinexample.domain.user.controller.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotExistsUsernameValidator::class])
annotation class NotExistsUsername(
    val message: String = "이미 사용중인 이메일입니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
