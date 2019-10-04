package application.services

import application.services.base.IntegrationTestBase
import org.junit.Assert
import org.junit.jupiter.api.Test

class TransactionServiceIntegrationTest: IntegrationTestBase() {

    private lateinit var transactionService: TransactionService
    private lateinit var accountService: AccountService
    private lateinit var balanceService: BalanceService

    fun setUp() {
        accountService = AccountServiceImpl(accountRepository)
        balanceService = BalanceServiceImpl(balanceRepository)
        transactionService = TransactionServiceImpl(transactionRepository, accountService, balanceService)
    }

    @Test
    fun `should deposit 1000 BRL to user`() {
        Assert.assertEquals(1, 12)
    }


    @Test
    fun `should deposit 1000 BRL to first user and transfer to another user`() {

    }

}