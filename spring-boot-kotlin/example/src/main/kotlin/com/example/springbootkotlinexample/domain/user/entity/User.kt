package com.example.springbootkotlinexample.domain.user.entity

import com.example.springbootkotlinexample.domain.user.entity.constants.GenderEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.Comment
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Entity(name = "tb_user")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 255, nullable = false, unique = true)
    @Comment("이메일")
    private val username: String,

    @Column(length = 255, nullable = false)
    @Comment("비밀번호")
    private val password: String,

    @Column(length = 255, nullable = false)
    @Comment("channel.io 에서 유저 구분 고유 아이디로 사용됨")
    val uuid: String,

    @Column(length = 255, nullable = false)
    @Comment("비밀번호 salt key")
    val salt: String,

    @Column(length = 255, nullable = false)
    @Comment("회원가입 이메일 인증 토큰")
    val verifyToken: String,

    @Column(length = 10, nullable = false)
    @Comment("이름")
    val name: String,

    @Column(length = 10, nullable = false)
    @Comment("활동명")
    val nickName: String,

    @Column(length = 11, nullable = false)
    @Comment("핸드폰번호")
    val phone: String,

    @Column(length = 100, nullable = true)
    val address: String? = null,

    @Column(length = 100, nullable = true)
    val addressDetail: String? = null,

    @Column(length = 1, nullable = true)
    @Comment("성별")
    val gender: GenderEnum? = null,

    @Column(nullable = true)
    @Comment("생년월")
    val birthDay: Date? = null,

    @Column(length = 1, nullable = false)
    @Comment("핸드폰 인증 여부")
    val isCheckedPhone: Boolean = false,

    @Column(length = 1, nullable = false)
    @Comment("이메일 인증 여부")
    val isCheckedEmail: Boolean = false,

    @Column(length = 1, nullable = false)
    @Comment("핸드폰 광고 수신 여부")
    val isReceivedPhone: Boolean = false,

    @Column(length = 1, nullable = false)
    @Comment("이메일 광고 수신 여부")
    val isReceivedEmail: Boolean = false,

    @Column(nullable = false)
    @Comment("로그인 실패 횟수")
    val loginFailCount: Int = 0,

    @Column(columnDefinition = "TEXT", nullable = true)
    @Comment("탈퇴 사유")
    val withdrawReason: String? = null,

    @Column(nullable = false)
    @Comment("블럭 여부")
    val isBlocked: Boolean = false,

    @Column(nullable = true)
    @Comment("마지막 로그인 일시")
    val lastLoginAt: Date? = null,

    @Column(columnDefinition = "TEXT", nullable = true)
    @Comment("관리용 회원 비고 정보")
    val adminRemark: String? = null
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()
    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
