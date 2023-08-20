package com.example.jpaquerydsl.grape.application.dto

import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.region.domain.Region
import com.querydsl.core.annotations.QueryProjection

data class GrapeSummaryResponse(
    val id: Long,
    val nameKorean: String,
    val nameEnglish: String,
    val regions: Set<RegionResponse>
) {
    @QueryProjection
    constructor(
        grape: Grape,
        regions: Set<RegionResponse>
    ) : this(
        id = grape.id,
        nameKorean = grape.nameKorean,
        nameEnglish = grape.nameEnglish,
        regions = regions
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