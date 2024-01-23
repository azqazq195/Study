package io.directional.wine.pairing.application.dto

import io.directional.wine.pairing.domain.Pairing

data class PairingRequest(
    val name: String
) {
    fun toEntity(): Pairing {
        return Pairing(
            name = name
        )
    }
}