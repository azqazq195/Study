package com.example.jpaquerydsl.share.application.dto

import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.share.domain.Share

data class ShareRequest(
    val share: Double,
    val grapeId: Long,
    val regionId: Long,
) {
    fun toEntity(
        grape: Grape,
        region: Region,
    ): Share {
        return Share(
            share = share,
            grape = grape,
            region = region
        )
    }
}