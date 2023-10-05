package com.example.payhere.domain.account_book.entity

import com.example.payhere.common.entity.BaseAuditorEntity
import com.example.payhere.domain.user.entity.User
import jakarta.persistence.*
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
data class AccountBook(
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

    @CreatedBy
    @JoinColumn(nullable = true, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    override var createdBy: User? = null,

    @LastModifiedBy
    @JoinColumn(nullable = true, updatable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    override var modifiedBy: User? = null,

    @Column(nullable = false, updatable = true)
    val note: String,

    @Column(nullable = false, updatable = true)
    val amount: Int,
) : BaseAuditorEntity {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as AccountBook

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdAt = $createdAt , modifiedAt = $modifiedAt , deletedAt = $deletedAt , note = $note , amount = $amount )"
    }

}
