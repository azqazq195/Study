package com.example.payhere.domain.account_book.service

import com.example.payhere.domain.account_book.controller.dto.AccountBookDto
import com.example.payhere.domain.account_book.controller.dto.AccountBookListDto
import com.example.payhere.domain.account_book.controller.dto.CreateAccountBookRequest
import com.example.payhere.domain.account_book.controller.dto.UpdateAccountBookRequest
import com.example.payhere.domain.account_book.entity.AccountBook
import com.example.payhere.domain.account_book.entity.mapper.AccountBookMapper
import com.example.payhere.domain.account_book.entity.repository.AccountBookRepository
import com.example.payhere.domain.account_book.exception.AccountBookNotFoundException
import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class AccountBookServiceTest {
    private val repository = mockk<AccountBookRepository>()
    private val mapper = mockk<AccountBookMapper>()
    private val service = AccountBookService(repository, mapper)

    @Test
    fun findById() {
        val id = 1L
        val createdBy = 1L
        val entity = mockk<AccountBook>()
        every { entity.id } returns id
        every { entity.createdBy!!.id } returns createdBy
        val dto = mockk<AccountBookDto>()
        every { repository.findById(id) } returns Optional.of(entity)
        every { mapper.getDto(entity) } returns dto

        assertEquals(dto, service.findById(id, createdBy))

        verify { repository.findById(id) }
        verify { mapper.getDto(entity) }
    }

    @Test
    fun findById_ThrowException() {
        val id = 1L
        val createdBy = 1L
        every { repository.findById(id) } returns Optional.empty()

        assertThrows<AccountBookNotFoundException> {
            service.findById(id, createdBy)
        }

        verify { repository.findById(id) }
    }

    @Test
    fun findAll() {
        val createdBy = 1L
        val entities = listOf(mockk<AccountBook>(), mockk())
        val dtos = listOf(mockk<AccountBookListDto>(), mockk())
        every { repository.findAllByCreatedById(createdBy) } returns entities
        every { mapper.getListDto(entities[0]) } returns dtos[0]
        every { mapper.getListDto(entities[1]) } returns dtos[1]

        assertEquals(dtos, service.findAll(createdBy))

        verify { repository.findAllByCreatedById(createdBy) }
        verify { mapper.getListDto(entities[0]) }
        verify { mapper.getListDto(entities[1]) }
    }

    @Test
    fun create() {
        val createRequest = mockk<CreateAccountBookRequest>()
        val entity = mockk<AccountBook>()
        val dto = mockk<AccountBookDto>()
        every { mapper.getEntity(createRequest) } returns entity
        every { repository.save(entity) } returns entity
        every { mapper.getDto(entity) } returns dto

        assertEquals(dto, service.create(createRequest))

        verify { mapper.getEntity(createRequest) }
        verify { repository.save(entity) }
        verify { mapper.getDto(entity) }
    }

    @Test
    fun update() {
        val id = 1L
        val createdBy = 1L
        val updateRequest = mockk<UpdateAccountBookRequest>()
        val entity = mockk<AccountBook>()
        every { entity.id } returns id
        every { entity.createdBy!!.id } returns createdBy
        val updatedEntity = mockk<AccountBook>()
        val updatedDto = mockk<AccountBookDto>()
        every { repository.findById(id) } returns Optional.of(entity)
        every { mapper.getUpdatedEntity(updateRequest, entity) } returns updatedEntity
        every { repository.save(updatedEntity) } returns updatedEntity
        every { mapper.getDto(updatedEntity) } returns updatedDto

        assertEquals(updatedDto, service.update(id, updateRequest, createdBy))

        verify { repository.findById(id) }
        verify { mapper.getUpdatedEntity(updateRequest, entity) }
        verify { repository.save(updatedEntity) }
        verify { mapper.getDto(updatedEntity) }
    }

    @Test
    fun deleteById() {
        val id = 1L
        val createdBy = 1L
        val entity = mockk<AccountBook>()
        every { entity.id } returns id
        every { entity.createdBy!!.id } returns createdBy
        every { repository.findById(id) } returns Optional.of(entity)
        every { repository.delete(entity) } just Runs

        service.deleteById(id, createdBy)

        verify { repository.findById(id) }
        verify { repository.delete(entity) }
    }

    @Test
    fun restoreAll() {
        val createdBy = 1L
        every { repository.restoreAllByCreatedById(createdBy) } just Runs

        service.restoreAll(createdBy)

        verify { repository.restoreAllByCreatedById(createdBy) }
    }

}