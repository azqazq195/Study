package com.example.payhere.domain.auth.service

import com.example.payhere.domain.user.exception.UserNotFoundException
import com.example.payhere.domain.user.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.access-token-expire-time}")
    private val accessTokenExpireTime: Long,
    private val userService: UserService
) {
    private lateinit var secretKey: String

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    // Jwt 토큰 생성
    fun createToken(userPk: String, roles: List<String> = emptyList()): String {
        val claims = Jwts.claims().setSubject(userPk)
        claims["roles"] = roles
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + accessTokenExpireTime))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
            .compact()
    }

    @Throws(UserNotFoundException::class)
    fun getAuthentication(token: String): Authentication {
        val principal = userService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(principal, token, principal.authorities)
    }

    fun getUserPk(token: String?): String {
        return Jwts
            .parserBuilder()
            .setSigningKey(Decoders.BASE64.decode(secretKey))
            .build()
            .parseClaimsJws(token).body.subject
    }

    fun resolveToken(req: HttpServletRequest): String? {
        return req.getHeader("Authorization")
    }

    fun validateToken(jwtToken: String?): Boolean {
        return try {
            val claims =
                Jwts.parserBuilder()
                    .setSigningKey(Decoders.BASE64.decode(secretKey))
                    .build()
                    .parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            // 만료 등 오류 처리 생략
            false
        }
    }
}