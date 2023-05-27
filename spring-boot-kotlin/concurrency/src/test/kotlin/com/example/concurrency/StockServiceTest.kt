package com.example.concurrency

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
class StockServiceTest {

    @Autowired
    private lateinit var stockService: StockService

    @Autowired
    private lateinit var stockRepository: StockRepository

    @Test
    fun `동시에 100개의 요청`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(threadCount)
        val latch = CountDownLatch(threadCount)

        val stock = createStock()
        repeat((0..threadCount).count()) {
            executorService.submit {
                try {
                    stockService.decrease(stock.id!!, 1)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        assertEquals(0, getStock().quantity)
    }

    /**
     * Transactional 을 붙이지 않아야 성공하는데 그 이유는?
     */
    @Test
    fun `동시에 100개의 요청_싱크로나이즈드`() {
        val threadCount = 100
        val executorService = Executors.newFixedThreadPool(threadCount)
        val latch = CountDownLatch(threadCount)

        val stock = createStock()
        repeat((0..threadCount).count()) {
            executorService.submit {
                try {
                    stockService.decreaseSynchronized(stock.id!!, 1)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        assertEquals(0, getStock().quantity)
    }

    private fun createStock(): Stock {
        val stock = Stock(productId = 1, quantity = 100)
        return stockRepository.save(stock)
    }

    private fun getStock() = stockRepository.findById(1).orElseThrow()
}