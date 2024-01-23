package io.directional.wine._fixture

import io.directional.wine.aroma.domain.Aroma
import io.directional.wine.grape.domain.Grape
import io.directional.wine.importer.domain.Importer
import io.directional.wine.pairing.domain.Pairing
import io.directional.wine.region.domain.Region
import io.directional.wine.wine.domain.Type
import io.directional.wine.wine.domain.Wine
import io.directional.wine.winery.domain.Winery

fun createWine(
    type: Type = Type.RED,
    nameKorean: String = "와인",
    nameEnglish: String = "wine",
    alcohol: Double = 14.0,
    acidity: Int = 1,
    body: Int = 1,
    sweetness: Int = 1,
    tannin: Int = 1,
    servingTemperature: Double = 1.0,
    score: Double = 1.0,
    price: Int = 200000,
    style: String? = "sexy",
    grade: String? = "good",
    winery: Winery = createWinery(),
    region: Region = createRegion(),
    grapes: MutableList<Grape> = mutableListOf(createGrape()),
    importer: Importer = createImporter(),
    aromas: MutableList<Aroma> = mutableListOf(createAroma()),
    pairings: MutableList<Pairing> = mutableListOf(createPairing()),
): Wine = Wine(
    type,
    nameKorean,
    nameEnglish,
    alcohol,
    acidity,
    body,
    sweetness,
    tannin,
    servingTemperature,
    score,
    price,
    style,
    grade,
    winery,
    region,
    grapes,
    importer,
    aromas,
    pairings,
)