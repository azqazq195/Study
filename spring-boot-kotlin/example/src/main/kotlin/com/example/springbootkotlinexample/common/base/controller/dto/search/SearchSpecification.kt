package com.example.springbootkotlinexample.common.base.controller.dto.search

import com.example.springbootkotlinexample.utils.SearchUtils
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.FetchParent
import jakarta.persistence.criteria.Order
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import kotlin.reflect.KClass

class SearchSpecification<T>(
    private val rootClass: KClass<*>,
    private val searchDto: SearchDto
) : Specification<T> {

    override fun toPredicate(root: Root<T>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate {
        val predicate = getPredicate(root, cb)
        val orders = getOrder(root, cb)
        query.orderBy(orders)
        return predicate
    }

    fun getPageable(): Pageable {
        return PageRequest.of(searchDto.page, searchDto.size)
    }

    private fun getPredicate(root: Root<T>, cb: CriteriaBuilder): Predicate {
        var predicate: Predicate = cb.equal(cb.literal(true), true)
        searchDto.filters.forEach { filter ->
            filter.init(rootClass)
            fetchRelations(root, filter.relationEntityList)
            predicate = filter.operator.build(root, cb, filter, predicate)
        }
        return predicate
    }

    private fun getOrder(root: Root<T>, cb: CriteriaBuilder): MutableList<Order> {
        val orders: MutableList<Order> = mutableListOf()
        searchDto.sorts.forEach { sort ->
            sort.init(rootClass)
            orders.add(sort.direction.build(root, cb, sort))
        }
        return orders
    }

    private fun fetchRelations(root: Root<T>, relationEntityList: MutableList<KClass<*>>) {
        if (relationEntityList.isEmpty()) {
            return
        }

        val entityList = mutableListOf(rootClass).apply { addAll(relationEntityList) }
        var fetchParent = root as FetchParent<*, *>
        for (i in 1 until entityList.size) {
            if (!isFetched(fetchParent, entityList[i])) {
                fetch(fetchParent, entityList[i - 1], entityList[i])
            }
            fetchParent = getFetch(fetchParent, entityList[i])!!
        }
    }

    private fun <F : FetchParent<*, *>, C : Any> isFetched(
        fetchParent: F,
        child: KClass<C>
    ): Boolean {
        return getFetch(fetchParent, child) != null
    }

    private fun <F : FetchParent<*, *>, C : Any> getFetch(
        fetchParent: F,
        clazz: KClass<C>
    ): FetchParent<*, *>? {
        return fetchParent.fetches.find { it.attribute.name.equals(SearchUtils.attributeName(clazz)) }
    }

    private fun <F : FetchParent<*, *>, P : Any, C : Any> fetch(fetch: F, parent: KClass<P>, child: KClass<C>) {
        fetch.fetch<P, C>(SearchUtils.attributeName(child))
    }
}
