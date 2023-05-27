package com.example.springbootkotlinexample.domain.brand.service

import com.example.springbootkotlinexample.common.base.service.AbstractService
import com.example.springbootkotlinexample.domain.brand.controller.dto.CreateBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.ReadBrandDto
import com.example.springbootkotlinexample.domain.brand.controller.dto.UpdateBrandDto
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import com.example.springbootkotlinexample.domain.brand.entity.mapper.BrandMapper
import com.example.springbootkotlinexample.domain.brand.entity.repository.BrandRepository
import org.springframework.stereotype.Service

@Service
class BrandService(
    private val brandMapper: BrandMapper,
    private val brandRepository: BrandRepository
) : AbstractService<Brand, CreateBrandDto, UpdateBrandDto, ReadBrandDto>(
    brandMapper,
    brandRepository
)
