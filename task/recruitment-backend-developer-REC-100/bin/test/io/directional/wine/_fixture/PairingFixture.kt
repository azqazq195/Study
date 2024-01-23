package io.directional.wine._fixture

import io.directional.wine.pairing.domain.Pairing

fun createPairing(
    name: String = "pairing"
): Pairing = Pairing(
    name
)