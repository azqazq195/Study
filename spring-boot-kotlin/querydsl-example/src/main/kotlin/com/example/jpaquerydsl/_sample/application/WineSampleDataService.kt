package com.example.jpaquerydsl._sample.application

import com.example.jpaquerydsl.aroma.application.dto.AromaRequest
import com.example.jpaquerydsl.importer.application.dto.ImporterRequest
import com.example.jpaquerydsl.pairing.application.dto.PairingRequest
import com.example.jpaquerydsl.wine.application.WineService
import com.example.jpaquerydsl.wine.application.dto.WineRequest
import com.example.jpaquerydsl.wine.domain.Type
import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class WineSampleDataService(
    private val wineService: WineService
) {
    @Async
    fun init() {
        val enFaker = Faker()
        val koFaker = Faker(fakerConfig { locale = "ko_KR" })

        val types = enumValues<Type>()
        val randomType = types[Random.nextInt(types.size)]

        val aromaNames = List(10000) { koFaker.random.randomString(3, 5, Locale.KOREAN) }.distinct()
        val pairingNames = List(10000) { koFaker.random.randomString(3, 5, Locale.KOREAN) }.distinct()

        repeat(1000000) {
            val request = WineRequest(
                randomType,
                koFaker.beer.name(),
                enFaker.beer.name(),
                Random.nextDouble(10.0, 50.0),
                Random.nextInt(1, 10),
                Random.nextInt(1, 10),
                Random.nextInt(1, 10),
                Random.nextInt(1, 10),
                Random.nextDouble(-20.0, 50.0),
                Random.nextDouble(0.0, 10.0),
                Random.nextInt(10000, 1000000),
                koFaker.random.randomString(5, false),
                koFaker.random.randomString(5, false),
                Random.nextLong(1, 200001),
                Random.nextLong(1, 200001),
                List(Random.nextInt(1, 10)) { Random.nextLong(1, 200001) },
                ImporterRequest(koFaker.company.name()),
                List(Random.nextInt(1, 10)) { AromaRequest(aromaNames.random()) }.distinct(),
                List(Random.nextInt(1, 10)) { PairingRequest(pairingNames.random()) }.distinct(),
            )

            wineService.create(request)
        }
    }
}