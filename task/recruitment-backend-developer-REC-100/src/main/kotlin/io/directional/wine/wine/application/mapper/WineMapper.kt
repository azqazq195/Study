package io.directional.wine.wine.application.mapper

import io.directional.wine.aroma.domain.repository.AromaRepository
import io.directional.wine.grape.domain.GrapeRepository
import io.directional.wine.grape.domain.getEntityById
import io.directional.wine.importer.domain.repository.ImporterRepository
import io.directional.wine.pairing.domain.repository.PairingRepository
import io.directional.wine.region.domain.repository.RegionRepository
import io.directional.wine.region.domain.repository.getEntityById
import io.directional.wine.wine.application.dto.WineRequest
import io.directional.wine.wine.domain.Wine
import io.directional.wine.winery.domain.repository.WineryRepository
import io.directional.wine.winery.domain.repository.getEntityById
import org.springframework.stereotype.Component

@Component
class WineMapper(
    private val wineryRepository: WineryRepository,
    private val regionRepository: RegionRepository,
    private val importerRepository: ImporterRepository,
    private val grapeRepository: GrapeRepository,
    private val aromaRepository: AromaRepository,
    private val pairingRepository: PairingRepository,
) {
    fun dtoToEntity(dto: WineRequest): Wine {
        val winery = dto.wineryId.let(wineryRepository::getEntityById)
        val region = dto.regionId.let(regionRepository::getEntityById)
        val grapes = dto.grapeIds.map(grapeRepository::getEntityById)
        val importer = dto.importer.let { importerRepository.findByName(it.name).orElse(it.toEntity()) }
        val aromas = dto.aromas.map { aromaRepository.findByName(it.name).orElse(it.toEntity()) }
        val pairings = dto.pairings.map { pairingRepository.findByName(it.name).orElse(it.toEntity()) }

        return dto.toEntity(winery, region, grapes, importer, aromas, pairings)
    }
}