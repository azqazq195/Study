package com.example.jpaquerydsl.region.domain.repository

import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.region.exception.NotFoundRegionException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegionRepository : JpaRepository<Region, Long>

fun RegionRepository.getEntityById(id: Long): Region = findById(id).orElseThrow(::NotFoundRegionException)