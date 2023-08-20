package com.example.jpaquerydsl.importer.application.dto

import com.example.jpaquerydsl.importer.domain.Importer

data class ImporterRequest(
    val name: String
) {
    fun toEntity(): Importer {
        return Importer(
            name = name
        )
    }
}