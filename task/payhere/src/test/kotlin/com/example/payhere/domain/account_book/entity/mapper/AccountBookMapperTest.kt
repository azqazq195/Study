package com.example.payhere.domain.account_book.entity.mapper

import com.example.payhere.domain.account_book.controller.dto.CreateAccountBookRequest
import com.example.payhere.domain.account_book.controller.dto.UpdateAccountBookRequest
import com.example.payhere.domain.account_book.entity.AccountBook
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class AccountBookMapperTest {

    @Autowired
    lateinit var accountBookMapper: AccountBookMapper

    @Test
    fun getDto() {
        val entity = AccountBook(
            id = 1L,
            note = "Test",
            amount = 100,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        val dto = accountBookMapper.getDto(entity)

        assertEquals(dto.id, entity.id)
        assertEquals(dto.note, entity.note)
        assertEquals(dto.amount, entity.amount)
        assertEquals(dto.createdAt, entity.createdAt)
        assertEquals(dto.modifiedAt, entity.modifiedAt)
    }

    @Test
    fun getListDto() {
        val entity = AccountBook(
            id = 1L,
            note = "Test",
            amount = 100,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        val dto = accountBookMapper.getListDto(entity)

        assertEquals(dto.id, entity.id)
        assertEquals(dto.note, entity.note)
        assertEquals(dto.amount, entity.amount)
    }

    @Test
    fun getEntity() {
        val createDto = CreateAccountBookRequest(
            note = "Test",
            amount = 100
        )

        val entity = accountBookMapper.getEntity(createDto)

        assertEquals(entity.note, createDto.note)
        assertEquals(entity.amount, createDto.amount)
    }

    @Test
    fun getUpdatedEntity() {
        val entity = AccountBook(
            id = 1L,
            note = "Test",
            amount = 100,
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        val updateDto = UpdateAccountBookRequest(
            note = "Test2",
            amount = 200
        )

        val updatedEntity = accountBookMapper.getUpdatedEntity(updateDto, entity)

        assertEquals(updatedEntity.id, entity.id)
        assertEquals(updatedEntity.note, updateDto.note)
        assertEquals(updatedEntity.amount, updateDto.amount)
        assertEquals(updatedEntity.createdAt, entity.createdAt)
        assertEquals(updatedEntity.modifiedAt, entity.modifiedAt)
    }
}