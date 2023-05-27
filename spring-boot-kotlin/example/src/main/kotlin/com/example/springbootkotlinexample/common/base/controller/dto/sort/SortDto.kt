package com.example.springbootkotlinexample.common.base.controller.dto.sort

import com.example.springbootkotlinexample.utils.SearchUtils
import kotlin.reflect.KClass

data class SortDto(
    val key: String,
    val direction: SortDirection
) {
    lateinit var property: String
    lateinit var relationEntityList: MutableList<KClass<*>>

    fun init(rootClass: KClass<*>) {
        property = SearchUtils.getProperty(key)
        relationEntityList = SearchUtils.getRelationEntityList(rootClass, mutableListOf(), key)
    }
}
