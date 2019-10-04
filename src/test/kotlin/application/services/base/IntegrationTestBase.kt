package application.services.base

import application.services.AccountService
import application.services.AccountServiceImpl
import infrastructure.config.AppDataSource
import infrastructure.config.FlywayConfig
import infrastructure.repositories.AccountRepository
import infrastructure.repositories.BalanceRepository
import infrastructure.repositories.TransactionRepository
import org.jdbi.v3.core.Jdbi
import javax.sql.DataSource

open class IntegrationTestBase {

    val transactionRepository: TransactionRepository = TransactionRepository(jdbiTest())
    val accountRepository: AccountRepository = AccountRepository(jdbiTest())
    val balanceRepository: BalanceRepository = BalanceRepository(jdbiTest())

    private fun testDataSource(): DataSource {
        return AppDataSource().dataSource()
    }

    private fun jdbiTest(): Jdbi {
        return Jdbi.create(testDataSource())
    }

    private fun migrate() = FlywayConfig().migrate(testDataSource())

}