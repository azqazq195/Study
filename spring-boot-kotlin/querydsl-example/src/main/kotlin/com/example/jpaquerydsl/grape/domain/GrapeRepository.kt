package com.example.jpaquerydsl.grape.domain

import com.example.jpaquerydsl.grape.exception.NotFoundGrapeException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GrapeRepository : JpaRepository<Grape, Long>

fun GrapeRepository.getEntityById(id: Long): Grape = findById(id).orElseThrow(::NotFoundGrapeException)