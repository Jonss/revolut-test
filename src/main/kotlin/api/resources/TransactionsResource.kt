package api.resources

import api.resources.dtos.request.TransactionRequestBody
import api.resources.dtos.response.TransactionResponseBody
import application.services.AccountService
import application.services.TransactionService
import domain.exceptions.BalanceException
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.transactions(
    transactionService: TransactionService,
    accountService: AccountService
) {

    route("/transactions") {
        post("/transfer") {

            val accountId = call.request.header("x-account-id")

            val originAccount = accountService.findAccountById(accountId)

            if (originAccount.isPresent.not()) {
                call.respond(HttpStatusCode.MethodNotAllowed, mapOf("error" to "Origin Account not found."))
                return@post
            }

            val requestBody = call.receive<TransactionRequestBody>()

            val destinyAccount = accountService.findAccount(requestBody.destiny.email)

            if (destinyAccount.isPresent.not()) {
                call.respond(HttpStatusCode.MethodNotAllowed, mapOf("error" to "Destiny Account not found."))
                return@post
            }

            try {
                val transfer = transactionService.transfer(
                    requestBody.amount,
                    originAccount.get(),
                    destinyAccount.get(),
                    requestBody.currency
                )

                val transferResponseBody = transfer.map { TransactionResponseBody(it) }

                call.respond(HttpStatusCode.Created, transferResponseBody)
            } catch (e: BalanceException) {
                call.respond(HttpStatusCode.MethodNotAllowed, mapOf("error" to e.message))
            }
        }
    }
}
