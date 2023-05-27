package com.example.springbootkotlinexample.domain.auth.jwt

import com.example.springbootkotlinexample.config.logger
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors


@Component
class TokenProvider(
    @Value("\${jwt.secret}")
    private val secret: String,
    @Value("\${jwt.access-token-expire-time}")
    private val accessTokenExpireTime: Long,
    @Value("\${jwt.refresh-token-expire-time}")
    private val refreshTokenExpireTime: Long,
) : InitializingBean {
    private val authoritiesKey: String = "auth"
    private val grantType: String = "Bearer"
    private val logger = logger()
    private lateinit var key: Key

    override fun afterPropertiesSet() {
        val keyBytes: ByteArray = Base64.getDecoder().decode(secret)
        this.key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createAccessToken(email: String, auth: Set<GrantedAuthority>): String {
        return createToken(email, auth, accessTokenExpireTime)
    }

    fun createRefreshToken(email: String, auth: Set<GrantedAuthority>): String {
        return createToken(email, auth, refreshTokenExpireTime)
    }

    /** token 생성 algorithm **/
    protected open fun createToken(email: String, auth: Set<GrantedAuthority>, tokenValid: Long): String {
        val claims = Jwts.claims().setSubject(email)

        claims[authoritiesKey] = auth.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","))

        // 현재시간
        val now = Date()
        return Jwts.builder()
            .setClaims(claims) // 토큰 발행 유저 정보
            .setIssuedAt(now) // 토큰 발행 시간
            .setExpiration(Date(now.time + tokenValid)) // 토큰 만료시간
            .signWith(key, SignatureAlgorithm.HS512) // 키와 알고리즘 설정
            .compact()
    }

    /** 인증 정보 조회 **/
    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
        val authorities: Collection<GrantedAuthority> =
            Arrays.stream(claims[authoritiesKey].toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()).map { role: String -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
        val principal = User(claims.subject, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    /** token 유효성 검증 **/
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            logger.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            logger.info("지원하지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            logger.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }
}