package com.example.springbootkotlinexample.common.base.entity.mapper

import org.springframework.data.domain.Page

interface IMapper<E, CD, UD, RD> {
    fun toDto(entity: E): RD
    fun toEntity(createDto: CD): E
    fun updateFromDto(updateDto: UD, entity: E): E

    fun toDto(entityList: Collection<E>): List<RD> = entityList.map { toDto(it) }.toMutableList()
    fun toEntity(createDtoList: Collection<CD>): List<E> = createDtoList.map { toEntity(it) }.toMutableList()

    fun toDto(entityPage: Page<E>): Page<RD> = entityPage.map { toDto(it) }
}
