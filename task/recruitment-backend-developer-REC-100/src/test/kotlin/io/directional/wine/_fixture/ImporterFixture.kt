package io.directional.wine._fixture

import io.directional.wine.importer.domain.Importer

fun createImporter(
    name: String = "importer"
): Importer = Importer(
    name
)