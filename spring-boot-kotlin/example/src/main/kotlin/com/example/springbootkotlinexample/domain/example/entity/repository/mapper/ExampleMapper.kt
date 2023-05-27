package com.example.springbootkotlinexample.domain.example.entity.repository.mapper

import com.example.springbootkotlinexample.common.base.entity.mapper.IMapper
import com.example.springbootkotlinexample.domain.example.controller.dto.CreateExampleDto
import com.example.springbootkotlinexample.domain.example.controller.dto.ReadExampleDto
import com.example.springbootkotlinexample.domain.example.controller.dto.UpdateExampleDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import org.springframework.stereotype.Component

@Component
class ExampleMapper : IMapper<Example, CreateExampleDto, UpdateExampleDto, ReadExampleDto> {
    override fun toDto(entity: Example): ReadExampleDto {
        return ReadExampleDto(
            name = entity.name,
            content = entity.content,
            count = entity.count,
            enum = entity.enum,
            createdAt = entity.audit.createdAt,
            modifiedAt = entity.audit.modifiedAt
        )
    }

    override fun toEntity(createDto: CreateExampleDto): Example {
        return Example(
            name = createDto.name!!,
            content = createDto.content!!,
            count = createDto.count!!,
            enum = createDto.enum!!
        )
    }

    override fun updateFromDto(updateDto: UpdateExampleDto, entity: Example): Example {
        return entity.copy(
            name = updateDto.name ?: entity.name,
            content = updateDto.content ?: entity.content,
            count = updateDto.count ?: entity.count,
            enum = updateDto.enum ?: entity.enum
        )
    }
}
