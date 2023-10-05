package com.example.payhere.common.entity.repository

import com.example.payhere.common.entity.BaseTimeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface BaseTimeRepository<T : BaseTimeEntity> : JpaRepository<T, Long> {
    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.deletedAt IS NULL AND e.id = :id")
    override fun existsById(id: Long): Boolean

    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    override fun findAll(): MutableList<T>

    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL AND e.id = :id")
    override fun findById(id: Long): Optional<T>

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deletedAt = CURRENT_TIMESTAMP WHERE e = :entity")
    override fun delete(entity: T)

}