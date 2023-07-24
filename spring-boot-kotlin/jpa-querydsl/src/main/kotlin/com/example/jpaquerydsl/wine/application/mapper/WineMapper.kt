package com.example.jpaquerydsl.wine.application.mapper

import com.example.jpaquerydsl.aroma.domain.repository.AromaRepository
import com.example.jpaquerydsl.grape.domain.GrapeRepository
import com.example.jpaquerydsl.grape.domain.getEntityById
import com.example.jpaquerydsl.importer.domain.repository.ImporterRepository
import com.example.jpaquerydsl.pairing.domain.repository.PairingRepository
import com.example.jpaquerydsl.region.domain.repository.RegionRepository
import com.example.jpaquerydsl.region.domain.repository.getEntityById
import com.example.jpaquerydsl.wine.application.dto.WineRequest
import com.example.jpaquerydsl.wine.domain.Wine
import com.example.jpaquerydsl.winery.domain.repository.WineryRepository
import com.example.jpaquerydsl.winery.domain.repository.getEntityById
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