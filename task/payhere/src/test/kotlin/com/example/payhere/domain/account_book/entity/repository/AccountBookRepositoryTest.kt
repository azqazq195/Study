package com.example.payhere.domain.account_book.entity.repository

import com.example.payhere.domain.account_book.entity.AccountBook
import com.example.payhere.domain.user.entity.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.time.LocalDateTime

@DataJpaTest
class AccountBookRepositoryTest {

    @Autowired
    private lateinit var accountBookRepository: AccountBookRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Test
    fun findAllByCreatedById() {
        // given
        val user1 = createUser(1)
        val user2 = createUser(2)

        val accountBook1 = createAccountBook(1, user1)
        val accountBook2 = createAccountBook(2, user1)
        createAccountBook(3, user2)

        // when
        val accountBooks = accountBookRepository.findAllByCreatedById(user1.id!!)

        // then
        assertThat(accountBooks).containsExactlyInAnyOrder(accountBook1, accountBook2)
    }

    @Test
    fun restoreAllByCreatedById() {
        // given
        val user1 = createUser(1)
        val user2 = createUser(2)

        val accountBook1 = createAccountBook(1, user1)
        val accountBook2 = createAccountBook(2, user1)
        createAccountBook(3, user2)


        // when
        accountBookRepository.delete(accountBook1)
        accountBookRepository.restoreAllByCreatedById(user1.id!!)
        val accountBooks = accountBookRepository.findAllByCreatedById(user1.id!!)


        // then
        assertThat(accountBooks).containsExactlyInAnyOrder(accountBook1, accountBook2)
    }

    private fun createUser(index: Int): User {
        val entity = entityManager.persist(
            User(
                email = "user$index@example.com",
                password = "password",
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now()
            )
        )
        entityManager.flush()
        return entity
    }

    private fun createAccountBook(index: Int, createdBy: User): AccountBook {
        val entity = entityManager.persist(
            AccountBook(
                note = "Account Book $index",
                amount = 100,
                createdBy = createdBy,
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now()
            )
        )
        entityManager.flush()
        return entity
    }

}

