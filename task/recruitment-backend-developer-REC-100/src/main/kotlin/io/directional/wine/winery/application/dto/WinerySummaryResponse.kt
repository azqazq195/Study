package io.directional.wine.winery.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.region.domain.Region
import io.directional.wine.winery.domain.Winery

data class WinerySummaryResponse(
    val id: Long,
    val nameKorean: String,
    val nameEnglish: String,
    val region: RegionResponse
) {
    @QueryProjection
    constructor(
        winery: Winery,
        region: RegionResponse
    ) : this(
        id = winery.id,
        nameKorean = winery.nameKorean,
        nameEnglish = winery.nameEnglish,
        region = region
    )

    data class RegionResponse(
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
}