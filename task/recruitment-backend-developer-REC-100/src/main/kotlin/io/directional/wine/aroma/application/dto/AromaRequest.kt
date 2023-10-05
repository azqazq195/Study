package io.directional.wine.aroma.application.dto

import io.directional.wine.aroma.domain.Aroma

data class AromaRequest(
    val name: String
) {
    fun toEntity(): Aroma {
        return Aroma(
            name = name
        )
    }
}