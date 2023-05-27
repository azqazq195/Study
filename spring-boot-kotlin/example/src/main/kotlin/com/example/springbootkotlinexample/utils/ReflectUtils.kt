package com.example.springbootkotlinexample.utils

import com.example.springbootkotlinexample.common.advice.exception.BadRequestException
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

class ReflectUtils {
    companion object {
        fun getPropertyKClass(clazz: KClass<*>, property: String): KClass<*> {
            val kProperty1 = clazz.declaredMemberProperties.find {
                it.name == property
            } ?: throw BadRequestException("class(${clazz.simpleName})에 유효하지 않은 property($property)입니다.")
            return kProperty1.returnType.classifier as KClass<*>
        }
    }
}
