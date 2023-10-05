package io.directional.wine._fixture

import io.directional.wine.grape.domain.Grape
import io.directional.wine.region.domain.Region
import io.directional.wine.share.domain.Share

fun createShare(
    share: Double = 1.0,
    grape: Grape = createGrape(),
    region: Region = createRegion()
): Share = Share(
    share,
    grape,
    region
)