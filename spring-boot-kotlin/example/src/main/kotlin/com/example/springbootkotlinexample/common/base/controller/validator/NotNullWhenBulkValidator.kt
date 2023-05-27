package com.example.springbootkotlinexample.common.base.controller.validator

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NotNullWhenBulkValidator(
    private val request: HttpServletRequest
) : ConstraintValidator<NotNullWhenBulk, Long> {
    override fun isValid(value: Long?, context: ConstraintValidatorContext?): Boolean {
        if (request.method.equals("PATCH") && request.requestURI.endsWith("/bulk")) {
            return value != null
        }
        return true
    }
}
