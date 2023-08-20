package com.example.jpaquerydsl.pairing.application.dto

import com.example.jpaquerydsl.pairing.domain.Pairing

data class PairingRequest(
    val name: String
) {
    fun toEntity(): Pairing {
        return Pairing(
            name = name
        )
    }
}