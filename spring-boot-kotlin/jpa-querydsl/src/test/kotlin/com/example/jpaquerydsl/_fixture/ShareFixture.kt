package com.example.jpaquerydsl._fixture

import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.share.domain.Share

fun createShare(
    share: Double = 1.0,
    grape: Grape = createGrape(),
    region: Region = createRegion()
): Share = Share(
    share,
    grape,
    region
)