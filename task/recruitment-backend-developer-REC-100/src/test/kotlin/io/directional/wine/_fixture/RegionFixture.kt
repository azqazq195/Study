package io.directional.wine._fixture

import io.directional.wine.grape.domain.Grape
import io.directional.wine.region.application.dto.RegionDetailsResponse
import io.directional.wine.region.application.dto.RegionRequest
import io.directional.wine.region.application.dto.RegionSearchParam
import io.directional.wine.region.application.dto.RegionSummaryResponse
import io.directional.wine.region.domain.Region
import io.directional.wine.wine.domain.Wine
import io.directional.wine.winery.domain.Winery

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
