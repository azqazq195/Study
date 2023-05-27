package com.example.springbootkotlinexample.common.base.controller

import com.example.springbootkotlinexample.common.base.controller.dto.ValidDtoList
import com.example.springbootkotlinexample.common.base.controller.dto.search.SearchDto
import com.example.springbootkotlinexample.common.base.controller.response.ResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface IController<CD, UD> {
    fun findById(@PathVariable id: Long): ResponseDto<Any>
    fun findAll(): ResponseDto<Any>
    fun search(@RequestBody searchDto: SearchDto): ResponseDto<Any>
    fun create(@RequestBody createDto: CD): ResponseDto<Any>
    fun createAll(@RequestBody validDtoList: ValidDtoList<CD>): ResponseDto<Any>
    fun update(@PathVariable id: Long, @RequestBody updateDto: UD): ResponseDto<Any>
    fun updateAll(@RequestBody validDtoList: ValidDtoList<UD>): ResponseDto<Any>
    fun delete(@PathVariable id: Long): ResponseDto<Any>
}
