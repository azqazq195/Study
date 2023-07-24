package com.example.jpaquerydsl.importer.application.dto

import com.example.jpaquerydsl.importer.domain.Importer
import com.querydsl.core.annotations.QueryProjection

data class ImporterSummaryResponse(
    val id: Long,
    val name: String,
) {
    @QueryProjection
    constructor(importer: Importer) : this(
        id = importer.id,
        name = importer.name
    )
}