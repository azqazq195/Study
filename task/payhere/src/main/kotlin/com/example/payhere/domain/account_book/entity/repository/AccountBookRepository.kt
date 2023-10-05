package com.example.payhere.domain.account_book.entity.repository

import com.example.payhere.common.entity.repository.BaseAuditorRepository
import com.example.payhere.domain.account_book.entity.AccountBook
import org.springframework.stereotype.Repository

@Repository
interface AccountBookRepository : BaseAuditorRepository<AccountBook> {
}