package com.example.jpaquerydsl.aroma.application.dto

import com.example.jpaquerydsl.aroma.domain.Aroma

data class AromaRequest(
    val name: String
) {
    fun toEntity(): Aroma {
        return Aroma(
            name = name
        )
    }
}