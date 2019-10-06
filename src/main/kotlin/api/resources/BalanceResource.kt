package api.resources

import api.resources.dtos.response.SimpleBalance
import application.services.AccountService
import application.services.BalanceService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.balances(
    balanceService: BalanceService,
    accountService: AccountService
) {
    route("/balances") {
        get {
            val accountId = call.request.header("x-account-id")

            val account = accountService.findAccountById(accountId)

            if (account.isPresent.not()) {
                call.respond(HttpStatusCode.MethodNotAllowed, mapOf("error" to "Account not found."))
                return@get
            }

            val balances = balanceService.findAllBalance(account.get())

            val simpleBalances = balances.map { SimpleBalance(it) }

            val responseBody = mapOf(
                "account" to account.get().toAccountResponse(),
                "balances" to simpleBalances
            )

            call.respond(HttpStatusCode.OK, responseBody)
        }
    }
}
