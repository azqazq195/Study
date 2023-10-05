package com.example.payhere.common.entity.repository

import com.example.payhere.common.entity.BaseAuditorEntity
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface BaseAuditorRepository<T : BaseAuditorEntity> : BaseTimeRepository<T> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL AND e.createdBy.id = :createdBy")
    fun findAllByCreatedById(createdBy: Long): List<T>

    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deletedAt = NULL WHERE e.createdBy.id = :createdBy")
    fun restoreAllByCreatedById(createdBy: Long)
}