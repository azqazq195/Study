package com.example.jpaquerydsl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class JpaQuerydslApplication

fun main(args: Array<String>) {
    runApplication<JpaQuerydslApplication>(*args)
}
