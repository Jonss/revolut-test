package infrastructure.repositories

import domain.exceptions.EntityNotFoundException
import integration.base.IntegrationTestBase
import java.util.UUID
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import stubs.AccountStub

class AccountRepositoryTest : IntegrationTestBase() {

    @Test
    fun `should create account and find by externalId`() {
        val account = AccountStub().build().get()

        accountRepository.save(account)

        val accountFound = accountRepository.findAccountByExternalId(account.externalId.toString()).get()

        assertNotNull(accountFound.id)
        assertNull(account.id)
        assertEquals(account.email, accountFound.email)
        assertEquals(account.externalId, accountFound.externalId)
        assertEquals(account.fullName, accountFound.fullName)
        assertEquals(account.nickName, accountFound.nickName)
        assertEquals(account.phoneNumber, accountFound.phoneNumber)
    }

    @Test
    fun `should create account and find by email`() {
        val account = AccountStub().build().get()

        accountRepository.save(account)

        val accountFound = accountRepository.findAccountByEmail(account.email).get()

        assertNotNull(accountFound.id)
        assertNull(account.id)
        assertEquals(account.email, accountFound.email)
        assertEquals(account.externalId, accountFound.externalId)
        assertEquals(account.fullName, accountFound.fullName)
        assertEquals(account.nickName, accountFound.nickName)
        assertEquals(account.phoneNumber, accountFound.phoneNumber)
    }

    @Test
    fun `should throw exception when account is searched by an unknown email`() {
        Assertions.assertThrows(EntityNotFoundException::class.java) {
            accountRepository.findAccountByEmail("marte.machado@gmail.com")
        }
    }

    @Test
    fun `should throw exception when account is searched by an unknown externalId`() {
        Assertions.assertThrows(EntityNotFoundException::class.java) {
            accountRepository.findAccountByExternalId(UUID.randomUUID().toString()).get()
        }
    }
}
