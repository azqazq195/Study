package com.example.jpaquerydsl.wine.domain.repository

import com.example.jpaquerydsl.wine.domain.Wine
import com.example.jpaquerydsl.wine.exception.NotFoundWineException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WineRepository : JpaRepository<Wine, Long>

fun WineRepository.getEntityById(id: Long): Wine =
    findById(id).orElseThrow(::NotFoundWineException)