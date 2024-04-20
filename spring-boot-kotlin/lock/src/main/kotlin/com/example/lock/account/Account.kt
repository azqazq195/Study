package com.example.lock.account

import com.example.lock.account.balance.dto.DepositRequest
import com.example.lock.account.balance.dto.WithdrawRequest
import com.example.lock.account.balance.exception.NotEnoughBalanceException
import jakarta.persistence.*

@Entity
class Account(
    balance: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null

    @Column(nullable = false)
    var balance: Long = balance
        private set

    @Column(nullable = false)
    var updateMill: Long = System.currentTimeMillis()
        private set

    @Column(nullable = false)
    var updateNano: Long = System.nanoTime()
        private set

    fun deposit(request: DepositRequest) {
        this.balance += request.amount
        this.updateTime()
    }

    fun withdraw(request: WithdrawRequest) {
        if (this.balance < request.amount) {
            throw NotEnoughBalanceException()
        }

        this.balance -= request.amount
        this.updateTime()
    }

    private fun updateTime() {
        this.updateMill = System.currentTimeMillis()
        this.updateNano = System.nanoTime()
    }
}