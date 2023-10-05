package io.directional.wine.pairing.application

import io.directional.wine.pairing.application.dto.PairingRequest
import io.directional.wine.pairing.domain.repository.PairingRepository
import io.directional.wine.pairing.domain.repository.getEntityById
import io.directional.wine.pairing.exception.NotFoundPairingException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PairingService(
    private val pairingRepository: PairingRepository
) {
    fun create(pairingRequest: PairingRequest) {
        val pairing = pairingRequest.toEntity()
        pairingRepository.save(pairing)
    }

    fun update(id: Long, pairingRequest: PairingRequest) {
        val pairing = pairingRepository.getEntityById(id)
        val updateValue = pairingRequest.toEntity()

        pairing.update(updateValue)
        pairingRepository.save(pairing)
    }

    fun delete(id: Long) {
        if (!pairingRepository.existsById(id)) throw NotFoundPairingException()
        pairingRepository.deleteById(id)
    }
}