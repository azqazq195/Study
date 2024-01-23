package io.directional.wine.wine.application.dto

import io.directional.wine.aroma.application.dto.AromaRequest
import io.directional.wine.aroma.domain.Aroma
import io.directional.wine.grape.domain.Grape
import io.directional.wine.importer.application.dto.ImporterRequest
import io.directional.wine.importer.domain.Importer
import io.directional.wine.pairing.application.dto.PairingRequest
import io.directional.wine.pairing.domain.Pairing
import io.directional.wine.region.domain.Region
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine
import io.directional.wine.winery.domain.Winery

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
