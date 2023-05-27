package com.example.springbootkotlinexample.domain.product.entity

import com.example.springbootkotlinexample.common.base.entity.AuditingEntity
import com.example.springbootkotlinexample.domain.brand.entity.Brand
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.NamedAttributeNode
import jakarta.persistence.NamedEntityGraph
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@NamedEntityGraph(
    name = "product.brand",
    attributeNodes = [NamedAttributeNode("brand")]
)
@Entity(name = "tb_product")
@EntityListeners(AuditingEntityListener::class)
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand,

    @Embedded
    val audit: AuditingEntity = AuditingEntity()
) {
    fun changeBrand(brand: Brand) = this.copy(brand = brand)
}
