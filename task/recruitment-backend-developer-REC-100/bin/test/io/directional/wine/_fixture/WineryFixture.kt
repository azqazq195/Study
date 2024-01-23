package io.directional.wine._fixture

import io.directional.wine.region.domain.Region
import io.directional.wine.winery.domain.Winery

fun createWinery(
    nameKorean: String = "와이너리",
    nameEnglish: String = "winery",
    region: Region = createRegion()
): Winery = Winery(
    nameKorean,
    nameEnglish,
    region,
)