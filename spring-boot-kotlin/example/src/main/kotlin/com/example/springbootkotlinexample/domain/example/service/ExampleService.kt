package com.example.springbootkotlinexample.domain.example.service

import com.example.springbootkotlinexample.common.base.service.AbstractService
import com.example.springbootkotlinexample.domain.example.controller.dto.CreateExampleDto
import com.example.springbootkotlinexample.domain.example.controller.dto.ReadExampleDto
import com.example.springbootkotlinexample.domain.example.controller.dto.UpdateExampleDto
import com.example.springbootkotlinexample.domain.example.entity.Example
import com.example.springbootkotlinexample.domain.example.entity.repository.ExampleRepository
import com.example.springbootkotlinexample.domain.example.entity.repository.mapper.ExampleMapper
import org.springframework.stereotype.Service

@Service
class ExampleService(
    private val exampleMapper: ExampleMapper,
    private val exampleRepository: ExampleRepository
) : AbstractService<Example, CreateExampleDto, UpdateExampleDto, ReadExampleDto>(
    exampleMapper,
    exampleRepository
)
