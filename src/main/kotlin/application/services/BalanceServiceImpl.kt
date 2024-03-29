package application.services

import domain.models.Account
import domain.models.Balance
import domain.models.Currency
import domain.models.Transaction
import infrastructure.repositories.BalanceRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.slf4j.LoggerFactory

class BalanceServiceImpl(private val balanceRepository: BalanceRepository) : BalanceService {

    private val logger = LoggerFactory.getLogger(BalanceServiceImpl::class.java)

    override fun findBalance(account: Account, currency: Currency): Balance {
        return balanceRepository.findBalance(account, currency)
    }

    override fun findAllBalance(account: Account): List<Balance> {
        return balanceRepository.findAllBalance(account)
    }

    override fun append(transaction: Transaction) {
        logger.info("Append {} {} to Account [{}]", transaction.currency, transaction.amount, transaction.destiny.externalId)
        balanceRepository.append(transaction)
    }

    override fun asyncAppend(transaction: Transaction) {
        GlobalScope.async {
            logger.info("Async at ${Thread.currentThread().name}")
            append(transaction)
        }
    }
}
