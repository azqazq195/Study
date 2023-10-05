package com.example.payhere.domain.user.entity

import com.example.payhere.common.entity.BaseTimeEntity
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity(name = "tb_user")
@EntityListeners(AuditingEntityListener::class)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    override val id: Long? = null,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    override var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column(nullable = false, updatable = true)
    override var modifiedAt: LocalDateTime? = null,

    @Column(nullable = true, updatable = true)
    override var deletedAt: LocalDateTime? = null,

    @Column(length = 255, nullable = false, unique = true)
    val email: String,

    @Column(length = 255, nullable = false)
    val password: String

) : BaseTimeEntity {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdAt = $createdAt , modifiedAt = $modifiedAt , deletedAt = $deletedAt , email = $email , password = $password )"
    }

}