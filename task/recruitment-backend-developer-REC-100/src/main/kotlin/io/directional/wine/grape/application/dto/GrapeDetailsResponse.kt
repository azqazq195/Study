package io.directional.wine.grape.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.grape.domain.Grape
import io.directional.wine.region.domain.Region
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine

data class GrapeDetailsResponse(
    val id: Long,
    val nameKorean: String,
    val nameEnglish: String,
    val acidity: Int,
    val body: Int,
    val sweetness: Int,
    val tannin: Int,
    val regions: Set<RegionResponse>?,
    val wines: Set<WineResponse>?
) {
    @QueryProjection
    constructor(
        grape: Grape,
        regions: Set<RegionResponse>,
        wines: Set<WineResponse>
    ) : this(
        id = grape.id,
        nameKorean = grape.nameKorean,
        nameEnglish = grape.nameEnglish,
        acidity = grape.acidity,
        body = grape.body,
        sweetness = grape.sweetness,
        tannin = grape.tannin,
        regions = regions,
        wines = wines
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

    data class WineResponse(
        val type: Type,
        val nameKorean: String,
        val nameEnglish: String,
        val alcohol: Double,
        val acidity: Int,
        val body: Int,
        val sweetness: Int,
        val tannin: Int,
        val servingTemperature: Double?,
        val score: Double,
        val price: Int,
        val style: String?,
        val grade: String?,
    ) {
        @QueryProjection
        constructor(wine: Wine) : this(
            type = wine.type,
            nameKorean = wine.nameKorean,
            nameEnglish = wine.nameEnglish,
            alcohol = wine.alcohol,
            acidity = wine.acidity,
            body = wine.body,
            sweetness = wine.sweetness,
            tannin = wine.tannin,
            servingTemperature = wine.servingTemperature,
            score = wine.score,
            price = wine.price,
            style = wine.style,
            grade = wine.grade,
        )
    }
}