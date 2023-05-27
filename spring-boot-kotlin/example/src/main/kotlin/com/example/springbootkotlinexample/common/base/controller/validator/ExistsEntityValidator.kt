package com.example.springbootkotlinexample.common.base.controller.validator

import jakarta.persistence.EntityManager
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ExistsEntityValidator(
    private val entityManager: EntityManager
) : ConstraintValidator<ExistsEntity, Long> {

    private var clazz: Class<*>? = null

    override fun initialize(constraintAnnotation: ExistsEntity) {
        this.clazz = constraintAnnotation.entity.java
    }

    override fun isValid(value: Long?, context: ConstraintValidatorContext?): Boolean {
        value ?: return true
        return entityManager.find(clazz!!, value) != null
    }
}
