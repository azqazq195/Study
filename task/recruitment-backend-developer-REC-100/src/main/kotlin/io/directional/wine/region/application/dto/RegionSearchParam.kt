package io.directional.wine.region.application.dto

data class RegionSearchParam(
    val nameKorean: String? = null,
    val nameEnglish: String? = null,
    val parentNameKorean: String? = null,
    val parentNameEnglish: String? = null,
)