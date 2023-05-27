package com.example.jwt._common.domain.repository

import com.example.jwt._common.domain.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<T : BaseEntity> : JpaRepository<T, Long>
