package com.example.concurrency

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val stockRepository: StockRepository
) {
    @Transactional
    fun decrease(stockId: Long, quantity: Long) {
        val stock = stockRepository.findById(stockId).orElseThrow();
        stockRepository.saveAndFlush(stock.decrease(quantity))
    }

    @Synchronized
    fun decreaseSynchronized(stockId: Long, quantity: Long) {
        val stock = stockRepository.findById(stockId).orElseThrow();
        stockRepository.saveAndFlush(stock.decrease(quantity))
    }
}