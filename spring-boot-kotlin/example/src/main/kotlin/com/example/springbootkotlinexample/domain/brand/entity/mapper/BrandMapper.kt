package com.example.springbootkotlinexample.domain.brand.entity.mapper

import com.example.springbootkotlinexample.common.base.entity.mapper.IMapper
import com.example.springbootkotlinexample.domain.brand.controller.dto.CreateBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.ReadBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.UpdateBrandDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import org.springframework.stereotype.Component

@Component
class BrandMapper() : IMapper<Brand, CreateBrandDto, UpdateBrandDto, ReadBrandDto> {
    override fun toDto(entity: Brand): ReadBrandDto {
        return ReadBrandDto(
            id = entity.id,
            name = entity.name,
            createdAt = entity.audit.createdAt,
            modifiedAt = entity.audit.modifiedAt
        )
    }

    override fun toEntity(createDto: CreateBrandDto): Brand {
        return Brand(
            name = createDto.name!!
        )
    }

    override fun updateFromDto(updateDto: UpdateBrandDto, entity: Brand): Brand {
        return entity.copy(
            name = updateDto.name ?: entity.name
        )
    }
}
