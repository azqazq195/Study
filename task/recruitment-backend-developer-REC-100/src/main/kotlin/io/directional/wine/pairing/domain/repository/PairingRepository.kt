package io.directional.wine.pairing.domain.repository

import io.directional.wine.pairing.domain.Pairing
import io.directional.wine.pairing.exception.NotFoundPairingException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PairingRepository : JpaRepository<Pairing, Long> {
    fun findByName(name: String): Optional<Pairing>
}

fun PairingRepository.getEntityById(id: Long): Pairing =
    findById(id).orElseThrow(::NotFoundPairingException)