package io.directional.wine.winery.application

import io.directional.wine.winery.application.dto.WineryRequest
import io.directional.wine.winery.application.mapper.WineryMapper
import io.directional.wine.winery.domain.repository.WineryRepository
import io.directional.wine.winery.domain.repository.getEntityById
import io.directional.wine.winery.exception.NotFoundWineryException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WineryService(
    private val wineryRepository: WineryRepository,
    private val wineryMapper: WineryMapper,
) {
    fun create(wineryRequest: WineryRequest) {
        val winery = wineryMapper.dtoToEntity(wineryRequest)
        wineryRepository.save(winery)
    }

    fun update(id: Long, wineryRequest: WineryRequest) {
        val winery = wineryRepository.getEntityById(id)
        val updateValue = wineryMapper.dtoToEntity(wineryRequest)

        winery.update(updateValue)
        wineryRepository.save(winery)
    }

    fun delete(id: Long) {
        if (!wineryRepository.existsById(id)) throw NotFoundWineryException()
        wineryRepository.deleteById(id)
    }
}