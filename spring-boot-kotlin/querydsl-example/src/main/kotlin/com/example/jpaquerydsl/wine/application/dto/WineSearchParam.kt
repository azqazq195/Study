package com.example.jpaquerydsl.wine.application.dto

import com.example.jpaquerydsl.wine.domain.Type

data class WineSearchParam(
    val type: Type?,
    val wineNameKorean: String?,
    val wineNameEnglish: String?,
    val minAlcohol: Int?,
    val maxAlcohol: Int?,
    val minPrice: Int?,
    val maxPrice: Int?,
    val style: String?,
    val grade: String?,
    val regionNameKorean: String?,
    val regionNameEnglish: String?,
)