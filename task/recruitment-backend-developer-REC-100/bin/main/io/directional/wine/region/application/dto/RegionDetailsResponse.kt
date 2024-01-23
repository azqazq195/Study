package io.directional.wine.region.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.grape.domain.Grape
import io.directional.wine.region.domain.Region
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine
import io.directional.wine.winery.domain.Winery

data class RegionDetailsResponse(
    val id: Long,
    val nameKorean: String,
    val nameEnglish: String,
    val parent: RegionResponse?,
    val grapes: Set<GrapeResponse>?,
    val wineries: Set<WineryResponse>?,
    val wines: Set<WineResponse>?,
) {
    @QueryProjection
    constructor(
        region: Region,
        grapes: Set<GrapeResponse>?,
        wineries: Set<WineryResponse>?,
        wines: Set<WineResponse>?,
    ) : this(
        id = region.id,
        nameKorean = region.nameKorean,
        nameEnglish = region.nameEnglish,
        parent = region.parent?.let(::RegionResponse),
        grapes = grapes,
        wineries = wineries,
        wines = wines,
    )

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
    }

    data class GrapeResponse(
        val id: Long,
        val nameKorean: String,
        val nameEnglish: String,
        val acidity: Int,
        val body: Int,
        val sweetness: Int,
        val tannin: Int,
    ) {
        @QueryProjection
        constructor(grape: Grape) : this(
            id = grape.id,
            nameKorean = grape.nameKorean,
            nameEnglish = grape.nameEnglish,
            acidity = grape.acidity,
            body = grape.body,
            sweetness = grape.sweetness,
            tannin = grape.tannin,
        )
    }

    data class WineryResponse(
        val id: Long,
        val nameKorean: String,
        val nameEnglish: String,
    ) {
        @QueryProjection
        constructor(winery: Winery) : this(
            id = winery.id,
            nameKorean = winery.nameKorean,
            nameEnglish = winery.nameEnglish,
        )
    }

    data class WineResponse(
        val id: Long,
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
            id = wine.id,
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