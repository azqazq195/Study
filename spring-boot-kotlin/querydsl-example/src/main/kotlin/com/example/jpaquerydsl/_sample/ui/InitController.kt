package com.example.jpaquerydsl._sample.ui

import com.example.jpaquerydsl._sample.application.GrapeSampleDataService
import com.example.jpaquerydsl._sample.application.RegionSampleDataService
import com.example.jpaquerydsl._sample.application.WineSampleDataService
import com.example.jpaquerydsl._sample.application.WinerySampleDataService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/init")
class InitController(
    private val regionSampleDataService: RegionSampleDataService,
    private val winerySampleDataService: WinerySampleDataService,
    private val grapeSampleDataService: GrapeSampleDataService,
    private val wineSampleDataService: WineSampleDataService,
) {
    @PostMapping
    fun init() {
        regionSampleDataService.init()
        winerySampleDataService.init()
        grapeSampleDataService.init()
        wineSampleDataService.init()
    }

    @PostMapping("/region")
    fun initRegion() {
        regionSampleDataService.init()
    }

    @PostMapping("/winery")
    fun initWinery() {
        winerySampleDataService.init()
    }

    @PostMapping("/grape")
    fun initGrape() {
        grapeSampleDataService.init()
    }

    @PostMapping("/wine")
    fun initWine() {
        wineSampleDataService.init()
    }
}