package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.aroma.domain.Aroma

fun createAroma(
    name: String = "aroma"
): Aroma = Aroma(
    name
)