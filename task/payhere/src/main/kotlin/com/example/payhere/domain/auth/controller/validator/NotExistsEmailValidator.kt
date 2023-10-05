package com.example.payhere.domain.auth.controller.validator

import com.example.payhere.domain.user.entity.repository.UserRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotExistsEmailValidator(
    private val userRepository: UserRepository
) : ConstraintValidator<NotExistsEmail, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        return !userRepository.findByEmail(value).isPresent
    }
}

