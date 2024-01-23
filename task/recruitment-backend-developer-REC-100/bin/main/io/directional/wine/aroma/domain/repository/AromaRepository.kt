package io.directional.wine.aroma.domain.repository

import io.directional.wine.aroma.domain.Aroma
import io.directional.wine.aroma.exception.NotFoundAromaException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AromaRepository : JpaRepository<Aroma, Long> {
    fun findByName(name: String): Optional<Aroma>
}

fun AromaRepository.getEntityById(id: Long): Aroma =
    findById(id).orElseThrow(::NotFoundAromaException)