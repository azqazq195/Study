package io.directional.wine.importer.application.dto

import io.directional.wine.importer.domain.Importer

data class ImporterRequest(
    val name: String
) {
    fun toEntity(): Importer {
        return Importer(
            name = name
        )
    }
}