package application

import api.resources.accounts
import api.resources.balances
import api.resources.fund
import api.resources.transactions
import application.services.AccountService
import application.services.BalanceService
import application.services.TransactionService
import com.fasterxml.jackson.databind.ObjectMapper
import infrastructure.config.FlywayConfig
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.JacksonConverter
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import javax.sql.DataSource

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(Koin) {
        modules(applicationModule)
    }

    val dataSource: DataSource by inject()

    FlywayConfig().migrate(dataSource)

    val objectMapper: ObjectMapper by inject()

    install(ContentNegotiation) {
        register(
            ContentType.Application.Json,
            JacksonConverter(objectMapper)
        )
    }

    val accountService: AccountService by inject()
    val transactionService: TransactionService by inject()
    val balanceService: BalanceService by inject()

    routing {
        accounts(accountService)
        fund(transactionService, accountService)
        transactions(transactionService, accountService)
        balances(balanceService, accountService)
    }
}
