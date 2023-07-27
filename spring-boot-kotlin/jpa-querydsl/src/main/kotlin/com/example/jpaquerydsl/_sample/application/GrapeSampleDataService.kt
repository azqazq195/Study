package com.example.jpaquerydsl._sample.application

import com.example.jpaquerydsl.grape.application.GrapeService
import com.example.jpaquerydsl.grape.application.dto.GrapeRequest
import com.example.jpaquerydsl.share.application.ShareService
import com.example.jpaquerydsl.share.application.dto.ShareRequest
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class GrapeSampleDataService(
    private val grapeService: GrapeService,
    private val shareService: ShareService,
) {
    @Async
    fun init() {
        val enFaker = Faker()
        val koFaker = Faker(fakerConfig { locale = "ko_KR" })

        repeat(200000) {
            val request = GrapeRequest(
                koFaker.food.fruits(),
                enFaker.food.fruits(),
                Random.nextInt(1, 10),
                Random.nextInt(1, 10),
                Random.nextInt(1, 10),
                Random.nextInt(1, 10),
            )
            grapeService.create(request)
        }

        for (i in 1L until 200001L) {
            val request = ShareRequest(
                Random.nextDouble(1.0, 10.0),
                i,
                Random.nextLong(1, 200001),
            )
            shareService.create(request)
        }
    }
}