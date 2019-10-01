package application

import application.services.AccountService
import application.services.AccountServiceImpl
import application.services.TransactionService
import application.services.TransactionServiceImpl
import infrastructure.repositories.AccountRepository
import infrastructure.repositories.TransactionRepository
import infrastructure.repositories.config.AppDataSource
import org.koin.dsl.module

val applicationModule = module {
    single { AppDataSource().create() }

    single<AccountService> { AccountServiceImpl(get()) }
    single { AccountRepository(get()) }

    single<TransactionService> { TransactionServiceImpl(get()) }
    single { TransactionRepository(get()) }
}

