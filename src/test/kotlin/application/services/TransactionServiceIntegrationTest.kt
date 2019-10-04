package application.services

import application.services.base.IntegrationTestBase
import domain.exceptions.BalanceException
import domain.models.Currency
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import stubs.AccountStub
import java.lang.ArithmeticException


open class TransactionServiceIntegrationTest: IntegrationTestBase() {

    private lateinit var transactionService: TransactionService
    private lateinit var accountService: AccountService
    private lateinit var balanceService: BalanceService

    @BeforeEach
    fun setUp() {
        migrate()
        accountService = AccountServiceImpl(accountRepository)
        balanceService = BalanceServiceImpl(balanceRepository)
        transactionService = TransactionServiceImpl(transactionRepository, accountService, balanceService)
    }


    @Test
    fun `should deposit 1000 BRL to user and verify balance`() {
        val createAccount = accountService.createAccount(AccountStub().build().get())

        val foundAccount = accountService.findAccountById(createAccount.externalId.toString()).get()

        transactionService.deposit(1000L, foundAccount, Currency.BRL)

        val findBalance = balanceService.findBalance(foundAccount, Currency.BRL)

        assertEquals(1000, findBalance.total)
        assertEquals(Currency.BRL, findBalance.currency)
    }


    @Test
    fun `should deposit 1000 BRL to first user and transfer to another user`() {
        val originAccountStub = AccountStub().build().get()
        val destinyAccountStub = AccountStub().build().get()

        val originAccount = accountService.createAccount(originAccountStub)
        val destinyAccount = accountService.createAccount(destinyAccountStub)

        val foundOriginAccount = accountService.findAccountById(originAccount.externalId.toString()).get()
        val foundDestinyAccount = accountService.findAccountById(destinyAccount.externalId.toString()).get()

        transactionService.deposit(1000, foundOriginAccount, Currency.BRL)

        transactionService.transfer(400, foundOriginAccount, foundDestinyAccount, Currency.BRL)

        val findOriginBalance = balanceService.findBalance(foundOriginAccount, Currency.BRL)

        val findDestinyBalance = balanceService.findBalance(foundDestinyAccount, Currency.BRL)

        assertEquals(600, findOriginBalance.total)
        assertEquals(Currency.BRL, findOriginBalance.currency)
        assertEquals(400, findDestinyBalance.total)
        assertEquals(Currency.BRL, findDestinyBalance.currency)
    }


    @Test
    fun `should throw Exception when user try to transfer GBP when user has no balance`() {
        val originAccountStub = AccountStub().build().get()
        val destinyAccountStub = AccountStub().build().get()

        val originAccount = accountService.createAccount(originAccountStub)
        val destinyAccount = accountService.createAccount(destinyAccountStub)

        val foundOriginAccount = accountService.findAccountById(originAccount.externalId.toString()).get()
        val foundDestinyAccount = accountService.findAccountById(destinyAccount.externalId.toString()).get()

        Assertions.assertThrows(BalanceException::class.java) {
            transactionService.transfer(1000000, foundOriginAccount, foundDestinyAccount, Currency.GBP)
        }
    }

}