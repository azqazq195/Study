package io.directional.wine.winery.application.dto

import io.directional.wine.region.domain.Region
import io.directional.wine.winery.domain.Winery

data class WineryRequest(
    val nameKorean: String,
    val nameEnglish: String,
    val regionId: Long,
) {
    fun toEntity(region: Region): Winery {
        return Winery(
            nameKorean = nameKorean,
            nameEnglish = nameEnglish,
            region = region
        )
    }
}