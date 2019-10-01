package application

import api.resources.accounts
import api.resources.deposit
import application.services.AccountService
import application.services.TransactionService
import com.fasterxml.jackson.databind.SerializationFeature
import infrastructure.repositories.config.FlywayConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>){
    FlywayConfig().migrate()
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
    }

    install(Koin) {
        modules(applicationModule)
    }

    val accountService: AccountService by inject()
    val transactionService: TransactionService by inject()

    routing {
        accounts(accountService)
        deposit(transactionService, accountService)
    }

}


