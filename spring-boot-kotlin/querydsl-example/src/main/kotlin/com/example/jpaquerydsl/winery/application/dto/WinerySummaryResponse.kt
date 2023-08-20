package com.example.jpaquerydsl.winery.application.dto

import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.winery.domain.Winery
import com.querydsl.core.annotations.QueryProjection

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