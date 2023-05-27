package com.example.springbootkotlinexample.domain.product.entity.repository

import com.example.springbootkotlinexample.common.base.entity.repository.IRepository
import com.example.springbootkotlinexample.domain.product.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : IRepository<Product> {
    @EntityGraph(value = "product.brand")
    override fun findById(id: Long): Optional<Product>

    @EntityGraph(value = "product.brand")
    override fun findAll(): MutableList<Product>

//    @EntityGraph(value = "product.brand")
    override fun findAll(spec: Specification<Product>?, pageable: Pageable): Page<Product>
}
