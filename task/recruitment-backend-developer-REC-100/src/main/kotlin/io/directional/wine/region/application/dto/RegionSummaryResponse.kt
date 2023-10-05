package io.directional.wine.region.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.region.domain.Region

data class RegionSummaryResponse(
    val id: Long,
    val nameKorean: String,
    val nameEnglish: String,
) {
    @QueryProjection
    constructor(region: Region) : this(
        id = region.id,
        nameKorean = region.nameKorean,
        nameEnglish = region.nameEnglish,
    )
}