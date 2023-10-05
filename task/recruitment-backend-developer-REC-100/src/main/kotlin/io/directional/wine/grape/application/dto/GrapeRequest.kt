package io.directional.wine.grape.application.dto

import io.directional.wine.grape.domain.Grape

data class GrapeRequest(
    val nameKorean: String,
    val nameEnglish: String,
    val acidity: Int,
    val body: Int,
    val sweetness: Int,
    val tannin: Int,
) {
    fun toEntity(): Grape {
        return Grape(
            nameKorean = nameKorean,
            nameEnglish = nameEnglish,
            acidity = acidity,
            body = body,
            sweetness = sweetness,
            tannin = tannin,
        )
    }
}