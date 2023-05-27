package com.example.springbootkotlinexample.domain.product.entity.mapper

import com.example.springbootkotlinexample.common.base.entity.mapper.IMapper
import com.example.springbootkotlinexample.common.base.service.exception.NotFoundEntityException
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.entity.mapper.BrandMapper
import com.example.springbootkotlinexample.domain.product.controller.dto.CreateProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.ReadProductDto
import com.example.springbootkotlinexample.domain.product.controller.dto.UpdateProductDto
import com.example.springbootkotlinexample.domain.product.entity.Product
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component

@Component
class ProductMapper(
    private val entityManager: EntityManager,
    private val brandMapper: BrandMapper
) : IMapper<Product, CreateProductDto, UpdateProductDto, ReadProductDto> {

    override fun toDto(entity: Product): ReadProductDto {
        return ReadProductDto(
            id = entity.id,
            name = entity.name,
            price = entity.price,
            brand = brandMapper.toDto(entity.brand),
            createdAt = entity.audit.createdAt,
            modifiedAt = entity.audit.modifiedAt
        )
    }

    override fun toEntity(createDto: CreateProductDto): Product {
        return Product(
            name = createDto.name!!,
            price = createDto.price!!,
            brand = findBrand(createDto.brandId!!)
        )
    }

    override fun updateFromDto(updateDto: UpdateProductDto, entity: Product): Product {
        return entity.copy(
            name = updateDto.name ?: entity.name,
            price = updateDto.price ?: entity.price,
            brand = updateDto.brandId?.let { findBrand(it) } ?: entity.brand
        )
    }

    fun findBrand(id: Long): Brand {
        return entityManager.find(Brand::class.java, id) ?: throw NotFoundEntityException(Brand::class.java.simpleName)
    }
}
