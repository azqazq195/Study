package io.directional.wine.winery.application.mapper

import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.region.domain.repository.getEntityById
import io.directional.wine.winery.application.dto.WineryRequest
import io.directional.wine.winery.domain.Winery
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