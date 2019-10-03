package application

import application.services.*
import com.fasterxml.jackson.databind.ObjectMapper
import infrastructure.repositories.AccountRepository
import infrastructure.repositories.BalanceRepository
import infrastructure.repositories.TransactionRepository
import infrastructure.config.AppDataSource
import infrastructure.providers.JsonProvider
import org.koin.dsl.module

val applicationModule = module {
    single { AppDataSource().create() }

    single<AccountService> { AccountServiceImpl(get()) }
    single { AccountRepository(get()) }

    single<TransactionService> { TransactionServiceImpl(get(), get(), get()) }
    single { TransactionRepository(get()) }

    single<BalanceService> { BalanceServiceImpl(get())}
    single { BalanceRepository(get()) }

    single<ObjectMapper> { JsonProvider().objectMapper() }
}

