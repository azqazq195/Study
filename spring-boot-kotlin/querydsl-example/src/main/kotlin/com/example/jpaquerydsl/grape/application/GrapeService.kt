package com.example.jpaquerydsl.grape.application

import com.example.jpaquerydsl.grape.application.dto.GrapeRequest
import com.example.jpaquerydsl.grape.domain.GrapeRepository
import com.example.jpaquerydsl.grape.domain.getEntityById
import com.example.jpaquerydsl.grape.exception.NotFoundGrapeException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GrapeService(
    private val grapeRepository: GrapeRepository,
) {
    fun create(grapeRequest: GrapeRequest) {
        val grape = grapeRequest.toEntity()
        grapeRepository.save(grape)
    }

    fun update(id: Long, grapeRequest: GrapeRequest) {
        val grape = grapeRepository.getEntityById(id)
        val updateValue = grapeRequest.toEntity()

        grape.update(updateValue)
        grapeRepository.save(grape)
    }

    fun delete(id: Long) {
        if (!grapeRepository.existsById(id)) throw NotFoundGrapeException()
        grapeRepository.deleteById(id)
    }
}