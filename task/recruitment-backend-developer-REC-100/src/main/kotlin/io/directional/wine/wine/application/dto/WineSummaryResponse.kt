package io.directional.wine.wine.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.region.domain.Region
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine

data class WineSummaryResponse(
    val id: Long,
    val type: Type,
    val nameKorean: String,
    val nameEnglish: String,
    val rootRegion: RootRegionResponse
) {
    @QueryProjection
    constructor(
        wine: Wine,
        region: RegionResponse,
    ) : this(
        id = wine.id,
        type = wine.type,
        nameKorean = wine.nameKorean,
        nameEnglish = wine.nameEnglish,
        rootRegion = region.getRootRegion()
    )

    data class RootRegionResponse(
        val id: Long,
        val nameKorean: String,
        val nameEnglish: String,
    ) {
        constructor(region: RegionResponse) : this(
            id = region.id,
            nameKorean = region.nameKorean,
            nameEnglish = region.nameEnglish,
        )
    }

    data class RegionResponse(
        val id: Long,
        val nameKorean: String,
        val nameEnglish: String,
        val parent: RegionResponse?,
    ) {
        @QueryProjection
        constructor(region: Region) : this(
            id = region.id,
            nameKorean = region.nameKorean,
            nameEnglish = region.nameEnglish,
            parent = region.parent?.let(::RegionResponse)
        )

        fun getRootRegion(): RootRegionResponse {
            var region = this
            while (region.parent != null) {
                region = region.parent!!
            }
            return region.let(::RootRegionResponse)
        }
    }
}
