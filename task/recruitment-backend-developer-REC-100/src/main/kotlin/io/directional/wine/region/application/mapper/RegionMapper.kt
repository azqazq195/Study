package io.directional.wine.region.application.mapper

import io.directional.wine.region.application.dto.RegionRequest
import io.directional.wine.region.domain.Region
import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.region.domain.repository.getEntityById
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