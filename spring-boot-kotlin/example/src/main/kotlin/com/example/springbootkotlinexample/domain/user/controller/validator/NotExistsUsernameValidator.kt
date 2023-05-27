package com.example.springbootkotlinexample.domain.user.controller.validator

import com.example.springbootkotlinexample.domain.user.entity.User
import jakarta.persistence.EntityManager
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotExistsUsernameValidator(
    private val entityManager: EntityManager
) : ConstraintValidator<NotExistsUsername, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {
        val typedQuery = entityManager.createQuery("SELECT u FROM tb_user u WHERE u.username = :username", User::class.java)
        typedQuery.setParameter("username", value)
        return typedQuery.resultList.size == 0
    }
}
