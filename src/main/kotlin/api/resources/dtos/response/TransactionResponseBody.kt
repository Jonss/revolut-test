package api.resources.dtos.response

import domain.models.Transaction

class TransactionResponseBody(transaction: Transaction) {
    private var amount = transaction.amount
    private var createdAt = transaction.createdAt
    private var externalId = transaction.createdAt
    private var operation = transaction.operation
    private var currency = transaction.currency
}
