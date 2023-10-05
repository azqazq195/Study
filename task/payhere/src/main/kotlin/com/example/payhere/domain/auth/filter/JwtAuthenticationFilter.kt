package com.example.payhere.domain.auth.filter

import com.example.payhere.common.response.ResponseCode
import com.example.payhere.common.response.ResponseDto
import com.example.payhere.common.utils.logger
import com.example.payhere.domain.auth.service.JwtTokenProvider
import com.example.payhere.domain.auth.support.JwtStorage
import com.example.payhere.domain.user.exception.UserNotFoundException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean


class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {
    private val log = logger()

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val token = jwtTokenProvider.resolveToken((request as HttpServletRequest))

        // 토큰 확인
        if (token == null) {
            filterChain.doFilter(request, response)
            return
        }

        // 유효성 검사
        if (!jwtTokenProvider.validateToken(token)) {
            filterChain.doFilter(request, response)
            return
        }

        // 로그아웃 검사
        if (JwtStorage.isSignOut(token)) {
            ResponseDto.writeResponse(ResponseCode.UNAUTHORIZED, response as HttpServletResponse)
            return
        }

        try {
            val auth: Authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = auth
            filterChain.doFilter(request, response)
        } catch (e: UserNotFoundException) {
            ResponseDto.writeResponse(ResponseCode.UNAUTHORIZED, response as HttpServletResponse)
        } catch (e: Exception) {
            log.error(e.message)
        }
    }

}