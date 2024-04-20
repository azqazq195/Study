package com.example.lock.account.balance

import com.example.lock.account.Account
import com.example.lock.account.balance.dto.DepositRequest
import com.example.lock.account.balance.dto.WithdrawRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.Mockito.*
import org.junit.jupiter.api.Assertions.*
import kotlin.concurrent.thread

@ExtendWith(MockitoExtension::class)
class BalanceControllerTest {

    @Mock
    private lateinit var balanceService: BalanceService

    @InjectMocks
    private lateinit var balanceController: BalanceController

    @Test
    fun `test balance`() {
        // given
        val accountId = 1L
        val expected = Account(100)
        `when`(balanceService.balance(accountId)).thenReturn(expected)

        // when
        val result = balanceController.balance(accountId)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `test deposit`() {
        // given
        val accountId = 1L
        val request = DepositRequest(100)
        val expected = Account(100)
        `when`(balanceService.deposit(accountId, request)).thenReturn(expected)

        // when
        val result = balanceController.deposit(accountId, request)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `test withdraw`() {
        // given
        val accountId = 1L
        val request = WithdrawRequest(100)
        val expected = Account(100)
        `when`(balanceService.withdraw(accountId, request)).thenReturn(expected)

        // when
        val result = balanceController.withdraw(accountId, request)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `test concurrency balance`() {
        // given
        val accountId = 1L
        val expected = Account(100)
        `when`(balanceService.balance(accountId)).thenReturn(expected)

        thread {

        }
        thread {

        }
    }
}
