package api.resources.dtos.request

import domain.models.Currency

data class TransactionRequestBody(
    val amount: Long,
    val destiny: AccountDestinyBody,
    val currency: Currency
)

data class AccountDestinyBody(
    val email: String
)
