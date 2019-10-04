package application

import application.services.AccountService
import application.services.AccountServiceImpl
import application.services.BalanceService
import application.services.BalanceServiceImpl
import application.services.TransactionService
import application.services.TransactionServiceImpl
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

    single<BalanceService> { BalanceServiceImpl(get()) }
    single { BalanceRepository(AppDataSource().create(get())) }

    single { JsonProvider().objectMapper() }
}
