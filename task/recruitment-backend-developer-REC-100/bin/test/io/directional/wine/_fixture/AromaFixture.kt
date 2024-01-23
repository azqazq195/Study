package io.directional.wine._fixture

import io.directional.wine.aroma.domain.Aroma

fun createAroma(
    name: String = "aroma"
): Aroma = Aroma(
    name
)