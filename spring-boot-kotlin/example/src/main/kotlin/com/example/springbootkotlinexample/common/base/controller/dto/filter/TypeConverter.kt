package com.example.springbootkotlinexample.common.base.controller.dto.filter

import com.example.springbootkotlinexample.common.advice.exception.InternalServerErrorException
import kotlin.reflect.KClass

class TypeConverter {
    companion object {
        fun toFiledType(clazz: KClass<*>): FieldType {
            return when (clazz) {
                String::class -> FieldType.STRING
                Int::class -> FieldType.INTEGER
                Long::class -> FieldType.LONG
                Double::class -> FieldType.DOUBLE
                Boolean::class -> FieldType.BOOLEAN
                else -> throw InternalServerErrorException("변환 타입 없음. ${clazz.simpleName}")
            }
        }
    }
}
