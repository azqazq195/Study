package com.example.jpaquerydsl._sample.application

import io.github.serpro69.kfaker.Faker
import io.github.serpro69.kfaker.fakerConfig
import org.junit.jupiter.api.Test

class RegionSampleDataServiceTest {

    // 20만개 데이터 생성
    @Test
    fun init() {
        val enFaker = Faker()
        val koFaker = Faker(fakerConfig { locale = "ko_KR" })


        println(enFaker.food.fruits())
        println(koFaker.food.fruits())
    }
}