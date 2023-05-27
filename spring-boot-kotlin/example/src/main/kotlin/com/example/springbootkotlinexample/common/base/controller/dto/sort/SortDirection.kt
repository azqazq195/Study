package com.example.springbootkotlinexample.common.base.controller.dto.sort

import com.example.springbootkotlinexample.utils.SearchUtils
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Root

enum class SortDirection {
    ASC {
        override fun <T> build(root: Root<T>, cb: CriteriaBuilder, sort: SortDto): Order {
            val path = SearchUtils.getRelationPath(root, sort.relationEntityList)
            return cb.asc(path.get<Any>(sort.property))
        }
    },
    DESC {
        override fun <T> build(root: Root<T>, cb: CriteriaBuilder, sort: SortDto): Order {
            val path = SearchUtils.getRelationPath(root, sort.relationEntityList)
            return cb.desc(path.get<Any>(sort.property))
        }
    };

    abstract fun <T> build(root: Root<T>, cb: CriteriaBuilder, sort: SortDto): Order
}
