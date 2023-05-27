package com.example.springbootkotlinexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SpringBootKotlinExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinExampleApplication>(*args)
}
