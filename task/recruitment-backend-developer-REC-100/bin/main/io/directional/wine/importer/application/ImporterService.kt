package io.directional.wine.importer.application

import io.directional.wine.importer.application.dto.ImporterRequest
import io.directional.wine.importer.domain.repository.ImporterRepository
import io.directional.wine.importer.domain.repository.getEntityById
import io.directional.wine.importer.exception.NotFoundImporterException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class ImporterService(
    private val importerRepository: ImporterRepository
) {
    fun create(importerRequest: ImporterRequest) {
        val importer = importerRequest.toEntity()
        importerRepository.save(importer)
    }

    fun update(id: Long, importerRequest: ImporterRequest) {
        val importer = importerRepository.getEntityById(id)
        val updateValue = importerRequest.toEntity()

        importer.update(updateValue)
        importerRepository.save(importer)
    }

    fun delete(id: Long) {
        if (!importerRepository.existsById(id)) throw NotFoundImporterException()
        importerRepository.deleteById(id)
    }
}