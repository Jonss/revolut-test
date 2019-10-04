package application

import application.services.*
import infrastructure.config.AppDataSource
import infrastructure.providers.JsonProvider
import infrastructure.repositories.AccountRepository
import infrastructure.repositories.BalanceRepository
import infrastructure.repositories.TransactionRepository
import org.koin.dsl.module

val applicationModule = module {
    single { AppDataSource().dataSource() }
    factory { AppDataSource().create(get()) }

    single<AccountService> { AccountServiceImpl(get()) }
    single { AccountRepository(AppDataSource().create(get())) }

    single<TransactionService> { TransactionServiceImpl(get(), get(), get()) }
    single { TransactionRepository(AppDataSource().create(get())) }

    single<BalanceService> { BalanceServiceImpl(get())}
    single { BalanceRepository(AppDataSource().create(get())) }

    single { JsonProvider().objectMapper() }

}

