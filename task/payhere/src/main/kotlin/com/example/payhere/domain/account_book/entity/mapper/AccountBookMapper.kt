package com.example.payhere.domain.account_book.entity.mapper

import com.example.payhere.domain.account_book.controller.dto.AccountBookDto
import com.example.payhere.domain.account_book.controller.dto.AccountBookListDto
import com.example.payhere.domain.account_book.controller.dto.CreateAccountBookRequest
import com.example.payhere.domain.account_book.controller.dto.UpdateAccountBookRequest
import com.example.payhere.domain.account_book.entity.AccountBook
import org.springframework.stereotype.Component

@Component
class AccountBookMapper {
    fun getDto(entity: AccountBook): AccountBookDto {
        return AccountBookDto(
            id = entity.id!!,
            note = entity.note,
            amount = entity.amount,
            createdAt = entity.createdAt!!,
            modifiedAt = entity.modifiedAt!!
        )
    }

    fun getListDto(entity: AccountBook): AccountBookListDto {
        return AccountBookListDto(
            id = entity.id!!,
            note = entity.note,
            amount = entity.amount,
        )
    }

    fun getEntity(createDto: CreateAccountBookRequest): AccountBook {
        return AccountBook(
            note = createDto.note!!,
            amount = createDto.amount!!,
        )
    }

    fun getUpdatedEntity(updateDto: UpdateAccountBookRequest, entity: AccountBook): AccountBook {
        return entity.copy(
            note = updateDto.note ?: entity.note,
            amount = updateDto.amount ?: entity.amount,
        )
    }
}