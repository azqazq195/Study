package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.region.application.dto.RegionDetailsResponse
import com.example.jpaquerydsl.region.application.dto.RegionRequest
import com.example.jpaquerydsl.region.application.dto.RegionSearchParam
import com.example.jpaquerydsl.region.application.dto.RegionSummaryResponse
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.wine.domain.Wine
import com.example.jpaquerydsl.winery.domain.Winery

fun createRegion(
    nameKorean: String = "한국",
    nameEnglish: String = "Korea",
    parent: Region? = null,
): Region = Region(
    nameKorean,
    nameEnglish,
    parent
)

fun createRegionRequest(
    nameKorean: String = "",
    nameEnglish: String = "",
    parentId: Long? = null,
): RegionRequest = RegionRequest(
    nameKorean,
    nameEnglish,
    parentId,
)

fun createRegionSearchParam(
    nameKorean: String? = null,
    nameEnglish: String? = null,
    parentNameKorean: String? = null,
    parentNameEnglish: String? = null,
): RegionSearchParam = RegionSearchParam(
    nameKorean,
    nameEnglish,
    parentNameKorean,
    parentNameEnglish
)

fun createRegionDetailsResponse(
    region: Region = createRegion(),
    grapes: Set<Grape> = setOf(createGrape()),
    wineries: Set<Winery> = setOf(createWinery()),
    wines: Set<Wine> = setOf(createWine()),
): RegionDetailsResponse = RegionDetailsResponse(
    region,
    grapes.map { RegionDetailsResponse.GrapeResponse(it) }.toSet(),
    wineries.map { RegionDetailsResponse.WineryResponse(it) }.toSet(),
    wines.map { RegionDetailsResponse.WineResponse(it) }.toSet(),
)

fun createRegionSummaryResponse(
    region: Region = createRegion(),
): RegionSummaryResponse = RegionSummaryResponse(
    region
)
