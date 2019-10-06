package infrastructure.repositories

import domain.models.Currency
import domain.models.Operation
import domain.models.Transaction
import integration.base.IntegrationTestBase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.Test
import stubs.AccountStub

class BalanceRepositoryTest : IntegrationTestBase() {

    @Test
    fun `should fund account with BRL and GBP and get latest amount of each currency`() {
        val account = AccountStub().build().get()
        accountRepository.save(account)

        val revolutAccount = accountRepository.findAccountByEmail("issuer@revolut.co.uk").get()
        val regularAccount = accountRepository.findAccountByEmail(account.email).get()

        val tx1 = Transaction(1000, revolutAccount, regularAccount, Operation.SAVING, Currency.BRL)
        val tx2 = Transaction(2500, revolutAccount, regularAccount, Operation.SAVING, Currency.GBP)
        val tx3 = Transaction(2000, revolutAccount, regularAccount, Operation.SAVING, Currency.BRL)
        val tx4 = Transaction(4500, revolutAccount, regularAccount, Operation.SAVING, Currency.GBP)
        val tx5 = Transaction(3000, revolutAccount, regularAccount, Operation.SAVING, Currency.GBP)

        listOf(tx1, tx2, tx3, tx4, tx5).forEach { tx ->
            balanceRepository.append(tx)
        }

        val balances = balanceRepository.findAllBalance(regularAccount)

        assertEquals(Currency.BRL, balances[0].currency)
        assertEquals(3000, balances[0].total)
        assertEquals(Currency.GBP, balances[1].currency)
        assertEquals(10000, balances[1].total)
    }

    @Test
    fun `should fund one account and then withdraw to a second account and get balance of each account`() {
        val primaryAccount = AccountStub().build().get()
        accountRepository.save(primaryAccount)

        val secondaryAccount = AccountStub().build().get()
        accountRepository.save(secondaryAccount)

        val revolutAccount = accountRepository.findAccountByEmail("issuer@revolut.co.uk").get()
        val firstAccount = accountRepository.findAccountByEmail(primaryAccount.email).get()
        val secondAccount = accountRepository.findAccountByEmail(secondaryAccount.email).get()

        // It emulates a funding
        val tx1 = Transaction(10000, revolutAccount, firstAccount, Operation.SAVING, Currency.BRL)

        balanceRepository.append(tx1)

        val firstBalanceOfFirstAccount = balanceRepository.findAllBalance(firstAccount)

        assertEquals(Currency.BRL, firstBalanceOfFirstAccount[0].currency)
        assertEquals(10000, firstBalanceOfFirstAccount[0].total)

        // It emulates a transfer
        val tx2 = Transaction(2500, firstAccount, secondAccount, Operation.TRANSFER, Currency.BRL)
        val tx3 = Transaction(2500, secondAccount, firstAccount, Operation.WITHDRAWAL, Currency.BRL)

        listOf(tx2, tx3).forEach { tx -> balanceRepository.append(tx) }

        val balanceOfFirstAccount = balanceRepository.findAllBalance(firstAccount)

        assertEquals(Currency.BRL, balanceOfFirstAccount[0].currency)
        assertEquals(7500, balanceOfFirstAccount[0].total)

        val balanceOfSecondAccount = balanceRepository.findAllBalance(secondAccount)

        assertEquals(Currency.BRL, balanceOfSecondAccount[0].currency)
        assertEquals(2500, balanceOfSecondAccount[0].total)
    }

    @Test
    fun `should get an emptyList when account has no balance`() {
        val account = AccountStub().build().get()
        accountRepository.save(account)

        val accountFound = accountRepository.findAccountByEmail(account.email).get()

        val balance = balanceRepository.findAllBalance(accountFound)

        assertTrue(balance.isEmpty())
    }
}
