package com.example.springbootkotlinexample.common.base.service

import com.example.springbootkotlinexample.common.base.controller.dto.IReadDto
import com.example.springbootkotlinexample.common.base.controller.dto.IUpdateDto
import com.example.springbootkotlinexample.common.base.controller.dto.search.SearchDto
import com.example.springbootkotlinexample.common.base.controller.dto.search.SearchSpecification
import com.example.springbootkotlinexample.common.base.entity.mapper.IMapper
import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.common.base.service.exception.NotFoundEntityException
import org.springframework.data.domain.Page
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class AbstractService<E, CD, UD, RD>(
    private val mapper: IMapper<E, CD, UD, RD>,
    private val repository: IRepository<E>
) : IService<E, CD, UD, RD> where E : Any, UD : IUpdateDto, RD : IReadDto {

    override fun entityClass(): KClass<*> {
        val type = javaClass.genericSuperclass as ParameterizedType
        return (type.actualTypeArguments[0] as Class<*>).kotlin
    }

    override fun entityName(): String {
        return entityClass().simpleName!!
    }

    override fun findById(id: Long): RD {
        val entity = repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
        return mapper.toDto(entity)
    }

    override fun findAll(): List<RD> {
        return mapper.toDto(repository.findAll())
    }

    override fun search(searchDto: SearchDto): Page<RD> {
        val specification: SearchSpecification<E> = SearchSpecification(entityClass(), searchDto)
        val pageable = specification.getPageable()
        return mapper.toDto(repository.findAll(specification, pageable))
    }

    override fun create(createDto: CD): RD {
        return mapper.toDto(repository.save(mapper.toEntity(createDto)))
    }

    override fun createAll(createListDto: List<CD>) {
        repository.saveAll(mapper.toEntity(createListDto))
    }

    override fun update(id: Long, updateDto: UD) {
        val entity = repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
        repository.save(mapper.updateFromDto(updateDto, entity))
    }

    override fun updateAll(updateListDto: List<UD>) {
        updateListDto.map { update(it.getId(), it) }
    }

    override fun deleteById(id: Long) {
        val entity = repository.findById(id).orElseThrow { NotFoundEntityException(entityName()) }
        repository.delete(entity)
    }
}
