package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.aroma.domain.Aroma
import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.importer.domain.Importer
import com.example.jpaquerydsl.pairing.domain.Pairing
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.wine.domain.Type
import com.example.jpaquerydsl.wine.domain.Wine
import com.example.jpaquerydsl.winery.domain.Winery

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