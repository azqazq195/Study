package com.example.jwt.auth.infrastructure

import com.example.jwt._common.exception.ErrorCode
import com.example.jwt._common.util.ExceptionUtils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class AccessDeniedHandlerImpl : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        ExceptionUtils.handleException(response, ErrorCode.FORBIDDEN)
    }
}