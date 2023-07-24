package com.example.jpaquerydsl.aroma.domain.repository

import com.example.jpaquerydsl.aroma.domain.Aroma
import com.example.jpaquerydsl.aroma.exception.NotFoundAromaException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AromaRepository : JpaRepository<Aroma, Long> {
    fun findByName(name: String): Optional<Aroma>
}

fun AromaRepository.getEntityById(id: Long): Aroma =
    findById(id).orElseThrow(::NotFoundAromaException)