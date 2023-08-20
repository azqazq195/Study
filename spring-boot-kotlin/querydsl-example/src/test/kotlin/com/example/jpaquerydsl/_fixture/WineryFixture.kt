package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.winery.domain.Winery

fun createWinery(
    nameKorean: String = "와이너리",
    nameEnglish: String = "winery",
    region: Region = createRegion()
): Winery = Winery(
    nameKorean,
    nameEnglish,
    region,
)