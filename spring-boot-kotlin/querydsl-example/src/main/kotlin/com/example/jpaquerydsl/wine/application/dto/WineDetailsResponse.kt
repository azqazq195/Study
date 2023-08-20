package com.example.jpaquerydsl.wine.application.dto

import com.example.jpaquerydsl.aroma.domain.Aroma
import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.importer.domain.Importer
import com.example.jpaquerydsl.pairing.domain.Pairing
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.wine.domain.Type
import com.example.jpaquerydsl.wine.domain.Wine
import com.example.jpaquerydsl.winery.domain.Winery
import com.querydsl.core.annotations.QueryProjection

data class WineDetailsResponse(
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
    val winery: WineryResponse,
    val region: RegionResponse,
    val grapes: Set<GrapeResponse>,
    val importer: ImporterResponse,
    val aromas: Set<AromaResponse>,
    val pairings: Set<PairingResponse>,
) {
    @QueryProjection
    constructor(
        wine: Wine,
        winery: WineryResponse,
        region: RegionResponse,
        grapes: Set<GrapeResponse>,
        importer: ImporterResponse,
        aromas: Set<AromaResponse>,
        pairings: Set<PairingResponse>,
    ) : this(
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
        winery = winery,
        region = region,
        grapes = grapes,
        importer = importer,
        aromas = aromas,
        pairings = pairings,
    )

    data class WineryResponse(
        val id: Long,
        val nameKorean: String,
        val nameEnglish: String,
        val region: RegionResponse
    ) {
        @QueryProjection
        constructor(
            winery: Winery,
            region: RegionResponse
        ) : this(
            id = winery.id,
            nameKorean = winery.nameKorean,
            nameEnglish = winery.nameEnglish,
            region = region
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
    }

    data class GrapeResponse(
        val id: Long,
        val nameKorean: String,
        val nameEnglish: String,
    ) {
        @QueryProjection
        constructor(grape: Grape) : this(
            id = grape.id,
            nameKorean = grape.nameKorean,
            nameEnglish = grape.nameEnglish,
        )
    }

    data class ImporterResponse(
        val id: Long,
        val name: String,
    ) {
        @QueryProjection
        constructor(importer: Importer) : this(
            id = importer.id,
            name = importer.name,
        )
    }

    data class AromaResponse(
        val id: Long,
        val name: String,
    ) {
        @QueryProjection
        constructor(aroma: Aroma) : this(
            id = aroma.id,
            name = aroma.name,
        )
    }

    data class PairingResponse(
        val id: Long,
        val name: String,
    ) {
        @QueryProjection
        constructor(pairing: Pairing) : this(
            id = pairing.id,
            name = pairing.name,
        )
    }
}
