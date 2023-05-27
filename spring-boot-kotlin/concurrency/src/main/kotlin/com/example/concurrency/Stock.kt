package com.example.concurrency

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productId: Long,
    val quantity: Long,
) {
    fun decrease(quantity: Long): Stock =
        this.copy(quantity = this.quantity - quantity)

}