package com.example.jpaquerydsl._sample.application

import com.example.jpaquerydsl.region.application.RegionService
import com.example.jpaquerydsl.region.application.dto.RegionRequest
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class RegionSampleDataService(
    private val regionService: RegionService
) {
    @Async
    fun init() {
        val enFaker = Faker()
        val koFaker = Faker(fakerConfig { locale = "ko_KR" })

        repeat(100) {
            val request = RegionRequest(koFaker.address.country(), enFaker.address.country(), null)
            regionService.create(request)
        }

        repeat(10000) {
            val request = RegionRequest(koFaker.address.state(), enFaker.address.state(), Random.nextLong(1, 101))
            regionService.create(request)
        }

        repeat(189900) {
            val request =
                RegionRequest(
                    koFaker.address.streetAddress(),
                    enFaker.address.streetAddress(),
                    Random.nextLong(101, 10001)
                )
            regionService.create(request)
        }
    }
}