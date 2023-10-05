package io.directional.wine.share.domain.repository

import io.directional.wine.share.domain.Share
import io.directional.wine.share.exception.NotFoundShareException
import org.springframework.data.jpa.repository.JpaRepository

interface ShareRepository : JpaRepository<Share, Long>

fun ShareRepository.getEntityById(id: Long): Share =
    findById(id).orElseThrow(::NotFoundShareException)