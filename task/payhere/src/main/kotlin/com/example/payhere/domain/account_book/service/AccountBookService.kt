package com.example.payhere.domain.account_book.service

import com.example.payhere.domain.account_book.controller.dto.AccountBookDto
import com.example.payhere.domain.account_book.controller.dto.AccountBookListDto
import com.example.payhere.domain.account_book.controller.dto.CreateAccountBookRequest
import com.example.payhere.domain.account_book.controller.dto.UpdateAccountBookRequest
import com.example.payhere.domain.account_book.entity.AccountBook
import com.example.payhere.domain.account_book.entity.mapper.AccountBookMapper
import com.example.payhere.domain.account_book.entity.repository.AccountBookRepository
import com.example.payhere.domain.account_book.exception.AccountBookNoPermissionException
import com.example.payhere.domain.account_book.exception.AccountBookNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AccountBookService(
    private val repository: AccountBookRepository,
    private val mapper: AccountBookMapper
) {
    @Transactional
    fun findById(id: Long, createdBy: Long): AccountBookDto {
        val entity = getEntity(id, createdBy)
        return mapper.getDto(entity)
    }

    @Transactional
    fun findAll(createdBy: Long): List<AccountBookListDto> {
        val entities = repository.findAllByCreatedById(createdBy)
        return entities.map { mapper.getListDto(it) }
    }

    @Transactional
    fun create(createRequest: CreateAccountBookRequest): AccountBookDto {
        val entity = repository.save(mapper.getEntity(createRequest))
        return mapper.getDto(entity)
    }

    @Transactional
    fun update(id: Long, updateRequest: UpdateAccountBookRequest, createdBy: Long): AccountBookDto {
        val entity = getEntity(id, createdBy)
        val updatedEntity = mapper.getUpdatedEntity(updateRequest, entity)
        repository.save(updatedEntity)
        return mapper.getDto(updatedEntity)
    }

    @Transactional
    fun deleteById(id: Long, createdBy: Long) {
        val entity = getEntity(id, createdBy)
        repository.delete(entity)
    }

    @Transactional
    fun restoreAll(createdBy: Long) {
        repository.restoreAllByCreatedById(createdBy)
    }

    private fun getEntity(id: Long, createdBy: Long): AccountBook {
        val entity = repository.findById(id).orElseThrow { AccountBookNotFoundException() }
        if (entity.createdBy!!.id != createdBy) throw AccountBookNoPermissionException()
        return entity
    }
}