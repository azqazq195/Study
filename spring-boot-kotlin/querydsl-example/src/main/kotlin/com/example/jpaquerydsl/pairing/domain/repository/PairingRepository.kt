package com.example.jpaquerydsl.pairing.domain.repository

import com.example.jpaquerydsl.pairing.domain.Pairing
import com.example.jpaquerydsl.pairing.exception.NotFoundPairingException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PairingRepository : JpaRepository<Pairing, Long> {
    fun findByName(name: String): Optional<Pairing>
}

fun PairingRepository.getEntityById(id: Long): Pairing =
    findById(id).orElseThrow(::NotFoundPairingException)