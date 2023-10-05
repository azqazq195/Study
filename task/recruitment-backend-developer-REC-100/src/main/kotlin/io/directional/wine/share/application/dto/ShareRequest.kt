package io.directional.wine.share.application.dto

import io.directional.wine.grape.domain.Grape
import io.directional.wine.region.domain.Region
import io.directional.wine.share.domain.Share

data class ShareRequest(
    val share: Double,
    val grapeId: Long,
    val regionId: Long,
) {
    fun toEntity(
        grape: Grape,
        region: Region,
    ): Share {
        return Share(
            share = share,
            grape = grape,
            region = region
        )
    }
}