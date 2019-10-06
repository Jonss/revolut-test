package integration.base

import infrastructure.config.AppDataSource
import infrastructure.config.FlywayConfig
import infrastructure.repositories.AccountRepository
import infrastructure.repositories.BalanceRepository
import infrastructure.repositories.TransactionRepository
import org.jdbi.v3.core.Jdbi
import org.junit.jupiter.api.BeforeEach

open class IntegrationTestBase {

    lateinit var accountRepository: AccountRepository
    lateinit var transactionRepository: TransactionRepository
    lateinit var balanceRepository: BalanceRepository

    @BeforeEach
    fun init() {
        accountRepository = AccountRepository(jdbiTest())
        transactionRepository = TransactionRepository(jdbiTest())
        balanceRepository = BalanceRepository(jdbiTest())
        migrate()
    }

    private fun migrate() = FlywayConfig().migrate(testDataSource())

    private fun testDataSource() = AppDataSource().dataSourceTest()

    private fun jdbiTest() = Jdbi.create(testDataSource())
}
