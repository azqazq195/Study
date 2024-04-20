package com.example.lock.account.balance

import com.example.lock.account.Account
import com.example.lock.account.AccountRepository
import com.example.lock.account.balance.dto.DepositRequest
import com.example.lock.account.balance.dto.WithdrawRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BalanceService(
    private val accountRepository: AccountRepository
) {
    @Transactional(readOnly = true)
    fun balance(id: Long): Account {
        return accountRepository.getReferenceById(id)
    }

    @Transactional
    fun deposit(id: Long, request: DepositRequest): Account {
        val account = accountRepository.findById(id).orElseThrow()
        account.deposit(request)
        return accountRepository.save(account)
    }

    @Transactional
    fun withdraw(id: Long, request: WithdrawRequest): Account {
        val account = accountRepository.findById(id).orElseThrow()
        account.withdraw(request)
        return accountRepository.save(account)
    }
}