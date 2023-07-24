package com.example.jpaquerydsl.winery.application.dto

import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.winery.domain.Winery

data class WineryRequest(
    val nameKorean: String,
    val nameEnglish: String,
    val regionId: Long,
) {
    fun toEntity(region: Region): Winery {
        return Winery(
            nameKorean = nameKorean,
            nameEnglish = nameEnglish,
            region = region
        )
    }
}