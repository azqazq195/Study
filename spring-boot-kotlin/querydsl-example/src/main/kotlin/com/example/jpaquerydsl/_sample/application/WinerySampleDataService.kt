package com.example.jpaquerydsl._sample.application

import com.example.jpaquerydsl.winery.application.WineryService
import com.example.jpaquerydsl.winery.application.dto.WineryRequest
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class WinerySampleDataService(
    private val wineryService: WineryService
) {
    @Async
    fun init() {
        val enFaker = Faker()
        val koFaker = Faker(fakerConfig { locale = "ko_KR" })

        repeat(200000) {
            val request = WineryRequest(
                koFaker.bank.name(),
                enFaker.bank.name(),
                Random.nextLong(1, 200001)
            )
            wineryService.create(request)
        }
    }
}