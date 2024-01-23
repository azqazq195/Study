package io.directional.wine.importer.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.importer.domain.Importer

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