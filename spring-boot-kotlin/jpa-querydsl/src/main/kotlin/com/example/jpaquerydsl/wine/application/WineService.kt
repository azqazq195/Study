package com.example.jpaquerydsl.wine.application

import com.example.jpaquerydsl.wine.application.dto.WineRequest
import com.example.jpaquerydsl.wine.application.mapper.WineMapper
import com.example.jpaquerydsl.wine.domain.repository.WineRepository
import com.example.jpaquerydsl.wine.domain.repository.getEntityById
import com.example.jpaquerydsl.wine.exception.NotFoundWineException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WineService(
    private val wineRepository: WineRepository,
    private val wineMapper: WineMapper
) {
    fun create(wineRequest: WineRequest) {
        val wine = wineMapper.dtoToEntity(wineRequest)
        wineRepository.save(wine)
    }

    fun update(id: Long, wineRequest: WineRequest) {
        val wine = wineRepository.getEntityById(id)
        val updateValue = wineMapper.dtoToEntity(wineRequest)

        wine.update(updateValue)
        wineRepository.save(wine)
    }

    fun delete(id: Long) {
        if (!wineRepository.existsById(id)) throw NotFoundWineException()
        wineRepository.deleteById(id)
    }
}