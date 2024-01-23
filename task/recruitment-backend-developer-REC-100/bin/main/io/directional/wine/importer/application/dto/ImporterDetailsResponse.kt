package io.directional.wine.importer.application.dto

import com.querydsl.core.annotations.QueryProjection
import io.directional.wine.importer.domain.Importer
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine

data class ImporterDetailsResponse(
    val id: Long,
    val name: String,
    val wines: Set<WineResponse>?
) {
    @QueryProjection
    constructor(
        importer: Importer,
        wines: Set<WineResponse>
    ) : this(
        id = importer.id,
        name = importer.name,
        wines = wines
    )

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