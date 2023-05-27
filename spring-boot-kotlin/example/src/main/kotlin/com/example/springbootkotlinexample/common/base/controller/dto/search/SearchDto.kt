package com.example.springbootkotlinexample.common.base.controller.dto.search

import com.example.springbootkotlinexample.common.base.controller.dto.filter.FilterDto
import com.example.springbootkotlinexample.common.base.controller.dto.sort.SortDto

data class SearchDto(
    val filters: List<FilterDto> = mutableListOf(),
    val sorts: List<SortDto> = mutableListOf(),
    val page: Int = 0,
    val size: Int = 20
)
