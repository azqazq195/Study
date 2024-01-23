package io.directional.wine.share.application.mapper

import io.directional.wine.grape.domain.GrapeRepository
import io.directional.wine.grape.domain.getEntityById
import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.region.domain.repository.getEntityById
import io.directional.wine.share.application.dto.ShareRequest
import io.directional.wine.share.domain.Share
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