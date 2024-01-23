package io.directional.wine.wine.application

import io.directional.wine.wine.application.dto.WineRequest
import io.directional.wine.wine.application.mapper.WineMapper
import io.directional.wine.wine.domain.repository.WineRepository
import io.directional.wine.wine.domain.repository.getEntityById
import io.directional.wine.wine.exception.NotFoundWineException
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