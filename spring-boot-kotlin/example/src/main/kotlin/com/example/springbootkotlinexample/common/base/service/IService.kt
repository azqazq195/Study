package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.search.SearchDto
import org.springframework.data.domain.Page
import kotlin.reflect.KClass

interface IService<E, CD, UD, RD> {
    fun entityClass(): KClass<*>
    fun entityName(): String
    fun findById(id: Long): RD
    fun findAll(): List<RD>
    fun search(searchDto: SearchDto): Page<RD>
    fun create(createDto: CD): RD
    fun createAll(createListDto: List<CD>)
    fun update(id: Long, updateDto: UD)
    fun updateAll(updateListDto: List<UD>)
    fun deleteById(id: Long)
}
