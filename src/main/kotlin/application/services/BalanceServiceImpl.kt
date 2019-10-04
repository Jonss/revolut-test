package application.services

import domain.models.Account
import domain.models.Balance
import domain.models.Currency
import domain.models.Transaction
import infrastructure.repositories.BalanceRepository

class BalanceServiceImpl(private val balanceRepository: BalanceRepository): BalanceService {

    override fun findBalance(account: Account, currency: Currency): Balance {
        return balanceRepository.findBalance(account, currency)
    }

    override fun findAllBalance(account: Account): List<Balance> {
        return  balanceRepository.findAllBalance(account)
    }

    override fun append(transaction: Transaction)  {
        balanceRepository.updateBalance(transaction)
    }
}