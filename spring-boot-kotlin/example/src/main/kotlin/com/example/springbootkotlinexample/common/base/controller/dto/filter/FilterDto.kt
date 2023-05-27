package com.example.springbootkotlinexample.common.base.controller.dto.filter

import com.example.springbootkotlinexample.utils.SearchUtils
import kotlin.reflect.KClass

data class FilterDto(
    val key: String,
    val operator: Operator,
    val value: Any? = null,
    val valueTo: Any? = null,
    val values: List<Any>? = null
) {
    lateinit var property: String
    lateinit var fieldType: FieldType
    lateinit var relationEntityList: MutableList<KClass<*>>

    fun init(rootClass: KClass<*>) {
        property = SearchUtils.getProperty(key)
        relationEntityList = SearchUtils.getRelationEntityList(rootClass, mutableListOf(), key)
        fieldType = SearchUtils.getPropertyType(
            if (relationEntityList.isEmpty()) rootClass else relationEntityList.last(),
            property
        )
    }
}
