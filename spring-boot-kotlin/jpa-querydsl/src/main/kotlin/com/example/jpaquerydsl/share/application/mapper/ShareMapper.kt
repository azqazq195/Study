package com.example.jpaquerydsl.share.application.mapper

import com.example.jpaquerydsl.grape.domain.GrapeRepository
import com.example.jpaquerydsl.grape.domain.getEntityById
import com.example.jpaquerydsl.region.domain.repository.RegionRepository
import com.example.jpaquerydsl.region.domain.repository.getEntityById
import com.example.jpaquerydsl.share.application.dto.ShareRequest
import com.example.jpaquerydsl.share.domain.Share
import org.springframework.stereotype.Component

@Component
class ShareMapper(
    private val grapeRepository: GrapeRepository,
    private val regionRepository: RegionRepository,
) {
    fun dtoToEntity(dto: ShareRequest): Share {
        val grape = dto.grapeId.let(grapeRepository::getEntityById)
        val region = dto.regionId.let(regionRepository::getEntityById)

        return dto.toEntity(grape, region)
    }
}