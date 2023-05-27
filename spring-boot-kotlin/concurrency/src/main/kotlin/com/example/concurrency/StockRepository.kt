package com.example.concurrency

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {
}