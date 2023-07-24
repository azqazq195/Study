package com.example.jpaquerydsl.winery.application.mapper

import com.example.jpaquerydsl.region.domain.repository.RegionRepository
import com.example.jpaquerydsl.region.domain.repository.getEntityById
import com.example.jpaquerydsl.winery.application.dto.WineryRequest
import com.example.jpaquerydsl.winery.domain.Winery
import org.springframework.stereotype.Component

@Component
class WineryMapper(
    private val regionRepository: RegionRepository
) {
    fun dtoToEntity(dto: WineryRequest): Winery {
        val region = dto.regionId.let(regionRepository::getEntityById)

        return dto.toEntity(region)
    }
}