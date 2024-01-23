package io.directional.wine.region.domain.repository

import io.directional.wine.region.domain.Region
import io.directional.wine.region.exception.NotFoundRegionException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegionRepository : JpaRepository<Region, Long>

fun RegionRepository.getEntityById(id: Long): Region = findById(id).orElseThrow(::NotFoundRegionException)