package io.directional.wine.importer.domain.repository

import io.directional.wine.importer.domain.Importer
import io.directional.wine.importer.exception.NotFoundImporterException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ImporterRepository : JpaRepository<Importer, Long> {
    fun findByName(name: String): Optional<Importer>
}

fun ImporterRepository.getEntityById(id: Long): Importer =
    findById(id).orElseThrow(::NotFoundImporterException)