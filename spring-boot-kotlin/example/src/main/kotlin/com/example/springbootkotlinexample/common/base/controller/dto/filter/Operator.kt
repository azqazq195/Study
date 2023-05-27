package com.example.springbootkotlinexample.common.base.controller.dto.filter

import com.example.springbootkotlinexample.utils.SearchUtils
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import java.time.LocalDateTime

enum class Operator {
    EQUAL {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val path = SearchUtils.getRelationPath(root, filter.relationEntityList)
            val property: Expression<Any> = path.get(filter.property)
            return cb.and(cb.equal(property, value), predicate)
        }
    },
    NOT_EQUAL {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val path = SearchUtils.getRelationPath(root, filter.relationEntityList)
            val property: Expression<Any> = path.get(filter.property)
            return cb.and(cb.notEqual(property, value), predicate)
        }
    },
    LIKE {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val path = SearchUtils.getRelationPath(root, filter.relationEntityList)
            val property: Expression<String> = path.get(filter.property)
            return cb.and(cb.like(cb.upper(property), "%" + value.toString().uppercase() + "%"), predicate)
        }
    },
    IN {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val values: List<Any> = filter.values!!
            val path = SearchUtils.getRelationPath(root, filter.relationEntityList)
            val property: Expression<Any> = path.get(filter.property)
            val inClause: CriteriaBuilder.In<Any> = cb.`in`(property)
            for (value in values) {
                inClause.value(filter.fieldType.parse(value.toString()))
            }
            return cb.and(inClause, predicate)
        }
    },
    BETWEEN {
        override fun <T> build(
            root: Root<T>,
            cb: CriteriaBuilder,
            filter: FilterDto,
            predicate: Predicate
        ): Predicate {
            val path = SearchUtils.getRelationPath(root, filter.relationEntityList)
            val value: Any = filter.fieldType.parse(filter.value.toString())
            val valueTo: Any = filter.fieldType.parse(filter.valueTo.toString())
            if (filter.fieldType === FieldType.DATE) {
                val startDate: LocalDateTime = value as LocalDateTime
                val endDate: LocalDateTime = valueTo as LocalDateTime
                val property: Expression<LocalDateTime> = path.get(filter.property)
                return cb.and(
                    cb.and(cb.greaterThanOrEqualTo(property, startDate), cb.lessThanOrEqualTo(property, endDate)),
                    predicate
                )
            }
            if (filter.fieldType !== FieldType.CHAR && filter.fieldType !== FieldType.BOOLEAN) {
                val start = value as Number
                val end = valueTo as Number
                val property: Expression<Number> = path.get(filter.property)
                return cb.and(cb.and(cb.ge(property, start), cb.le(property, end)), predicate)
            }
            return predicate
        }
    };

    abstract fun <T> build(
        root: Root<T>,
        cb: CriteriaBuilder,
        filter: FilterDto,
        predicate: Predicate
    ): Predicate
}
