package com.example.payhere.domain.auth.filter

import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        ResponseDto.writeResponse(ResponseCode.UNAUTHORIZED, response)
    }
}