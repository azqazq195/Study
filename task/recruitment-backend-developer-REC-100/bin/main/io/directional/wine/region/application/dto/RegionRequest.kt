package io.directional.wine.region.application.dto

import io.directional.wine.region.domain.Region

data class RegionRequest(
    val nameKorean: String,
    val nameEnglish: String,
    val parentId: Long?,
) {
    fun toEntity(parent: Region?): Region {
        return Region(
            nameKorean = nameKorean,
            nameEnglish = nameEnglish,
            parent = parent,
        )
    }
}