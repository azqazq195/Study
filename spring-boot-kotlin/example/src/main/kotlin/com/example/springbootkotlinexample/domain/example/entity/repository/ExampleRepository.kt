package com.example.springbootkotlinexample.domain.example.entity.repository

import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.domain.example.entity.Example
import org.springframework.stereotype.Repository

@Repository
interface ExampleRepository : IRepository<Example>
