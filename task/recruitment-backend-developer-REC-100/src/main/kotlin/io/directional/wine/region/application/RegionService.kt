package io.directional.wine.region.application

import io.directional.wine.region.application.dto.RegionRequest
import io.directional.wine.region.application.mapper.RegionMapper
import io.directional.wine.region.application.validator.RegionValidator
import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.region.domain.repository.getEntityById
import io.directional.wine.region.exception.NotFoundRegionException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegionService(
    private val regionRepository: RegionRepository,
    private val regionMapper: RegionMapper,
    private val regionValidator: RegionValidator,
) {
    fun create(regionRequest: RegionRequest) {
        val region = regionMapper.dtoToEntity(regionRequest)
        regionRepository.save(region)
    }

    fun update(id: Long, regionRequest: RegionRequest) {
        val region = regionRepository.getEntityById(id)
        val updateValue = regionMapper.dtoToEntity(regionRequest)

        region.update(updateValue)
        regionValidator.validate(region)
        regionRepository.save(region)
    }

    fun delete(id: Long) {
        if (!regionRepository.existsById(id)) throw NotFoundRegionException()
        regionRepository.deleteById(id)
    }
}