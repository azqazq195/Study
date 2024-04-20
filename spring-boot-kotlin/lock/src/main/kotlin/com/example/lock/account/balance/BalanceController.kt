package com.example.lock.account.balance

import com.example.lock.account.Account
import com.example.lock.account.balance.dto.DepositRequest
import com.example.lock.account.balance.dto.WithdrawRequest
import com.example.lock.aop.Lock
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class BalanceController(
    private val balanceService: BalanceService
) {
    @GetMapping("{id}/balance")
    fun balance(@PathVariable id: Long): Account {
        return balanceService.balance(id)
    }

    @PostMapping("{id}/deposit")
    @Lock(key = "#id", wait = false)
    fun deposit(@PathVariable id: Long, @RequestBody request: DepositRequest): Account {
        return balanceService.deposit(id, request)
    }

    @PostMapping("{id}/withdraw")
    @Lock(key = "#id")
    fun withdraw(@PathVariable id: Long, @RequestBody request: WithdrawRequest): Account {
        return balanceService.withdraw(id, request)
    }
}