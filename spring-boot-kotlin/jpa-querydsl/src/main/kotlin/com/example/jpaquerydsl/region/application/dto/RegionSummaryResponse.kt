package com.example.jpaquerydsl.region.application.dto

import com.example.jpaquerydsl.region.domain.Region
import com.querydsl.core.annotations.QueryProjection

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