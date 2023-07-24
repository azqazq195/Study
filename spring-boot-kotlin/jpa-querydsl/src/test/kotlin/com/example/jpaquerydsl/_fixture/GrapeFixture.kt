package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.grape.domain.Grape

fun createGrape(
    nameKorean: String = "포도",
    nameEnglish: String = "Grape",
    acidity: Int = 1,
    body: Int = 1,
    sweetness: Int = 1,
    tannin: Int = 1,
): Grape = Grape(
    nameKorean,
    nameEnglish,
    acidity,
    body,
    sweetness,
    tannin,
)