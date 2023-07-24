package com.example.jpaquerydsl.winery.application

import com.example.jpaquerydsl.winery.application.dto.WineryRequest
import com.example.jpaquerydsl.winery.application.mapper.WineryMapper
import com.example.jpaquerydsl.winery.domain.repository.WineryRepository
import com.example.jpaquerydsl.winery.domain.repository.getEntityById
import com.example.jpaquerydsl.winery.exception.NotFoundWineryException
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