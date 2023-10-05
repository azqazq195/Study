package io.directional.wine.wine.domain.repository

import io.directional.wine.wine.domain.Wine
import io.directional.wine.wine.exception.NotFoundWineException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WineRepository : JpaRepository<Wine, Long>

fun WineRepository.getEntityById(id: Long): Wine =
    findById(id).orElseThrow(::NotFoundWineException)