package com.example.jpaquerydsl._common.utils

import com.example.jpaquerydsl._common.domain.BaseEntity
import com.example.jpaquerydsl._common.exception.NonUniqueResultException
import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.querydsl.core.ResultTransformer
import com.querydsl.core.types.FactoryExpression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.domain.Pageable

object QueryDslUtils {
    fun <T> fetchSearchQuery(
        entity: EntityPathBase<*>,
        response: FactoryExpression<T>,
        query: JPAQuery<*>,
        pageable: Pageable,
    ): List<T> {
        val orderSpecifiers = getOrderSpecifiers(entity, pageable)
        return query.clone()
            .select(response)
            .orderBy(*orderSpecifiers)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    fun <T> fetchJoinSearchQuery(
        entity: EntityPathBase<*>,
        response: FactoryExpression<T>,
        query: JPAQuery<*>,
        pageable: Pageable,
    ): List<T> {
        val orderSpecifiers = getOrderSpecifiers(entity, pageable)
        return query.clone()
            .select(response)
            .orderBy(*orderSpecifiers)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetchJoin()
            .fetch()
    }

    fun <T> transformSearchQuery(
        entity: EntityPathBase<*>,
        response: ResultTransformer<List<T>>,
        query: JPAQuery<*>,
        pageable: Pageable,
    ): List<T> {
        val orderSpecifiers = getOrderSpecifiers(entity, pageable)
        return query.clone()
            .orderBy(*orderSpecifiers)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .transform(response)
    }

    fun fetchTotalSearchQuery(query: JPAQuery<*>, entity: EntityPathBase<*>): Long {
        return query.clone()
            .select(entity.count())
            .fetchOne() ?: 0L
    }

    private fun getOrderSpecifiers(entity: EntityPathBase<*>, pageable: Pageable): Array<OrderSpecifier<*>> {
        return pageable.sort.map { order ->
            val direction = if (order.isAscending) Order.ASC else Order.DESC
            val pathBuilder = PathBuilder(entity.type, entity.metadata)
            val path = Expressions.stringPath(pathBuilder, order.property)
            OrderSpecifier(direction, path)
        }.toList().toTypedArray()
    }
}

fun <D, E : BaseEntity> MutableList<D>.getSingleResultFrom(entityClass: Class<E>): D {
    if (this.isEmpty()) throw NotFoundEntityException(entityClass)
    if (this.size > 2) throw NonUniqueResultException(entityClass)
    return this.first()
}