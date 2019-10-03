package api.resources

import api.resources.dtos.request.DepositRequestBody
import application.services.AccountService
import application.services.TransactionService
import domain.models.Currency
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

fun Routing.deposit(
    transactionService: TransactionService,
    accountService: AccountService
) {
    post("/deposit") {

        val depositRequestBody = call.receive<DepositRequestBody>()

        val account = accountService.findAccount(depositRequestBody.account.email)

        if(account.isPresent.not()) {
            call.respond(HttpStatusCode.MethodNotAllowed, mapOf("error" to "Account Destiny not found."))
            return@post
        }

        val deposit = transactionService.deposit(
            depositRequestBody.value.amount,
            account.get(),
            Currency.valueOf(depositRequestBody.value.currency)
        )

        val depositResponseBody = deposit.toDepositResponseBody()

        call.respond(HttpStatusCode.Created, depositResponseBody)
    }
}