package api.resources.dtos.response

import domain.models.Balance

class SimpleBalance(balance: Balance) {
    val total = balance.total
    val currency = CurrencyDTO(balance.currency.name, balance.currency.symbol, balance.currency.fullName)
    val lastOperation = balance.lastDeposit
}
