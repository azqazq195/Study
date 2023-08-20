package com.example.jpaquerydsl.region.application.mapper

import com.example.jpaquerydsl.region.application.dto.RegionRequest
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.region.domain.repository.RegionRepository
import com.example.jpaquerydsl.region.domain.repository.getEntityById
import org.springframework.stereotype.Component

@Component
class RegionMapper(
    private val regionRepository: RegionRepository
) {
    fun dtoToEntity(dto: RegionRequest): Region {
        val parent = dto.parentId?.let(regionRepository::getEntityById)

        return dto.toEntity(parent)
    }
}