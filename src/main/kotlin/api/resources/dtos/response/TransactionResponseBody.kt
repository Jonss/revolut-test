package api.resources.dtos.response

import domain.models.Transaction

class TransactionResponseBody(transaction: Transaction) {
    val from = SimpleAccountResponse(transaction.origin.email, transaction.origin.fullName)
    val to = SimpleAccountResponse(transaction.destiny.email, transaction.destiny.fullName)
    val amount = transaction.amount
    val createdAt = transaction.createdAt
    val externalId = transaction.createdAt
    val operation = transaction.operation
    val currency = transaction.currency
}
