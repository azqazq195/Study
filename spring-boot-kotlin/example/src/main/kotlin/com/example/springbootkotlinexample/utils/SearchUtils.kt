package com.example.springbootkotlinexample.utils

import com.example.springbootkotlinexample.common.base.controller.dto.filter.FieldType
import com.example.springbootkotlinexample.common.base.controller.dto.filter.TypeConverter
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Root
import kotlin.reflect.KClass

class SearchUtils {
    companion object {
        private const val SEPARATOR = "."

        fun attributeName(clazz: KClass<*>): String {
            return clazz.simpleName!!.lowercase()
        }

        fun getRelationPath(root: Root<*>, relationEntityList: MutableList<KClass<*>>): Path<*> {
            var expression: Path<*> = root
            relationEntityList.forEach {
                expression = getPath(expression, it)
            }
            return expression
        }

        private fun <C : Any> getPath(path: Path<*>, clazz: KClass<C>): Path<*> {
            return path.get<C>(attributeName(clazz))
        }

        fun getProperty(key: String): String {
            return key.split(".").last()
        }

        fun getPropertyType(clazz: KClass<*>, property: String): FieldType {
            val fieldClass = ReflectUtils.getPropertyKClass(clazz, property)
            return TypeConverter.toFiledType(fieldClass)
        }

        fun getRelationEntityList(
            rootClass: KClass<*>,
            relationEntityList: MutableList<KClass<*>>,
            key: String
        ): MutableList<KClass<*>> {
            if (!hasRelationEntity(key)) {
                return relationEntityList
            }

            val relationEntityProperty = key.split(SEPARATOR)[0]
            val entity = if (relationEntityList.isEmpty()) rootClass else relationEntityList.last()
            relationEntityList.add(ReflectUtils.getPropertyKClass(entity, relationEntityProperty))

            return getRelationEntityList(
                rootClass,
                relationEntityList,
                key.substring(key.indexOf(SEPARATOR) + 1)
            )
        }

        private fun hasRelationEntity(key: String): Boolean {
            return key.contains(SEPARATOR)
        }
    }
}
