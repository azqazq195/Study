package com.example.payhere.domain.account_book.controller

import com.example.payhere.common.response.*
import com.example.payhere.domain.account_book.controller.dto.AccountBookDto
import com.example.payhere.domain.account_book.controller.dto.AccountBookListDto
import com.example.payhere.domain.account_book.controller.dto.CreateAccountBookRequest
import com.example.payhere.domain.account_book.controller.dto.UpdateAccountBookRequest
import com.example.payhere.domain.account_book.service.AccountBookService
import com.example.payhere.domain.auth.support.CurrentUser
import com.example.payhere.domain.user.entity.User
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account-book")
class AccountBookController(
    private val service: AccountBookService
) {
    @GetMapping("/{id}")
    fun findById(
        @CurrentUser user: User,
        @PathVariable id: Long
    ): ResponseEntity<SingleResult<AccountBookDto>> {
        val dto = service.findById(id, user.id!!)
        return ResponseDto.of(ResponseCode.SUCCESS, dto)
    }

    @GetMapping()
    fun findAll(
        @CurrentUser user: User
    ): ResponseEntity<ListResult<AccountBookListDto>> {
        val dtos = service.findAll(user.id!!)
        return ResponseDto.of(ResponseCode.SUCCESS, dtos)
    }

    @PostMapping()
    fun create(
        @RequestBody @Valid createAccountBookRequest: CreateAccountBookRequest
    ): ResponseEntity<SingleResult<AccountBookDto>> {
        val dto = service.create(createAccountBookRequest)
        return ResponseDto.of(ResponseCode.SUCCESS, dto)
    }

    @PatchMapping("/{id}")
    fun update(
        @CurrentUser user: User,
        @PathVariable id: Long,
        @RequestBody @Valid updateAccountBookRequest: UpdateAccountBookRequest
    ): ResponseEntity<SingleResult<AccountBookDto>> {
        val dto = service.update(id, updateAccountBookRequest, user.id!!)
        return ResponseDto.of(ResponseCode.SUCCESS, dto)
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @CurrentUser user: User,
        @PathVariable id: Long
    ): ResponseEntity<EmptyResult> {
        service.deleteById(id, user.id!!)
        return ResponseDto.of(ResponseCode.SUCCESS)
    }

    @PatchMapping("/restore-all")
    fun restoreAll(
        @CurrentUser user: User
    ): ResponseEntity<EmptyResult> {
        service.restoreAll(user.id!!)
        return ResponseDto.of(ResponseCode.SUCCESS)
    }
}