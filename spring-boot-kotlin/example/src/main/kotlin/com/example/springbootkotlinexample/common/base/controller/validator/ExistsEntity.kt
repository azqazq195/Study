package com.example.springbootkotlinexample.common.base.controller.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ExistsEntityValidator::class])
annotation class ExistsEntity(
    val entity: KClass<*>,

    val message: String = "존재하지 않는 엔티티입니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
