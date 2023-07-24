package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.pairing.domain.Pairing

fun createPairing(
    name: String = "pairing"
): Pairing = Pairing(
    name
)