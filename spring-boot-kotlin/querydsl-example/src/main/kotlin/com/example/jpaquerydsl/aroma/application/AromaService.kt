package com.example.jpaquerydsl.aroma.application

import com.example.jpaquerydsl.aroma.application.dto.AromaRequest
import com.example.jpaquerydsl.aroma.domain.repository.AromaRepository
import com.example.jpaquerydsl.aroma.domain.repository.getEntityById
import com.example.jpaquerydsl.aroma.exception.NotFoundAromaException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AromaService(
    private val aromaRepository: AromaRepository
) {
    fun create(aromaRequest: AromaRequest) {
        val aroma = aromaRequest.toEntity()
        aromaRepository.save(aroma)
    }

    fun update(id: Long, aromaRequest: AromaRequest) {
        val aroma = aromaRepository.getEntityById(id)
        val updateValue = aromaRequest.toEntity()

        aroma.update(updateValue)
        aromaRepository.save(aroma)
    }

    fun delete(id: Long) {
        if (!aromaRepository.existsById(id)) throw NotFoundAromaException()
        aromaRepository.deleteById(id)
    }
}