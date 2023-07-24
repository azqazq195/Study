package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.importer.domain.Importer

fun createImporter(
    name: String = "importer"
): Importer = Importer(
    name
)