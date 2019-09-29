package api.resources

import api.resources.dtos.AccountRequestBody
import application.AccountService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.accounts(accountService: AccountService) {
    route("/accounts") {
        post {
            val accountRequestBody = call.receive<AccountRequestBody>()
            val account = accountRequestBody.toAccount()

            accountService.createAccount(account)

            call.respond(HttpStatusCode.Created, account)
        }
    }

}