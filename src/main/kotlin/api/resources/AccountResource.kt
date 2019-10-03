package api.resources

import api.resources.dtos.request.AccountRequestBody
import application.services.AccountService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.accounts(accountService: AccountService) {

    route("/accounts") {
        post {
            val accountRequestBody = call.receive<AccountRequestBody>()
            val account = accountRequestBody.toAccount()

            try {
                accountService.createAccount(account)

                call.respond(HttpStatusCode.Created, account.toAccountResponse())
            } catch (e: Exception) {
                call.respond(HttpStatusCode.MethodNotAllowed, mapOf("error" to "e.message"))
            }
        }

        get("/{email}") {
            val email = call.parameters["email"]

            val account = accountService.findAccount(email)

            if(account.isPresent) {
                call.respond(HttpStatusCode.OK, account.get().toAccountResponse())
                return@get
            }

            call.respond(HttpStatusCode.NotFound, mapOf("error" to "Account not found."))
        }
    }

}