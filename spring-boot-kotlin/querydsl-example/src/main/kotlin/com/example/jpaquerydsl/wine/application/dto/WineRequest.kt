package com.example.jpaquerydsl.wine.application.dto

import com.example.jpaquerydsl.aroma.application.dto.AromaRequest
import com.example.jpaquerydsl.aroma.domain.Aroma
import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.importer.application.dto.ImporterRequest
import com.example.jpaquerydsl.importer.domain.Importer
import com.example.jpaquerydsl.pairing.application.dto.PairingRequest
import com.example.jpaquerydsl.pairing.domain.Pairing
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.wine.domain.Type
import com.example.jpaquerydsl.wine.domain.Wine
import com.example.jpaquerydsl.winery.domain.Winery

data class WineRequest(
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
    val style: String? = null,
    val grade: String? = null,
    val wineryId: Long,
    val regionId: Long,
    val grapeIds: List<Long>,
    val importer: ImporterRequest,
    val aromas: List<AromaRequest>,
    val pairings: List<PairingRequest>,
) {
    fun toEntity(
        winery: Winery,
        region: Region,
        grapes: List<Grape>,
        importer: Importer,
        aromas: List<Aroma>,
        pairings: List<Pairing>
    ): Wine {
        return Wine(
            type = type,
            nameKorean = nameKorean,
            nameEnglish = nameEnglish,
            alcohol = alcohol,
            acidity = acidity,
            body = body,
            sweetness = sweetness,
            tannin = tannin,
            servingTemperature = servingTemperature,
            score = score,
            price = price,
            style = style,
            grade = grade,
            winery = winery,
            region = region,
            grapes = grapes.toMutableList(),
            importer = importer,
            aromas = aromas.toMutableList(),
            pairings = pairings.toMutableList(),
        )
    }
}
