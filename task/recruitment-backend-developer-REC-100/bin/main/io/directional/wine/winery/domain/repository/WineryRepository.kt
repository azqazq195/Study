package io.directional.wine.winery.domain.repository

import io.directional.wine.winery.domain.Winery
import io.directional.wine.winery.exception.NotFoundWineryException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WineryRepository : JpaRepository<Winery, Long>

fun WineryRepository.getEntityById(id: Long): Winery =
    findById(id).orElseThrow(::NotFoundWineryException)