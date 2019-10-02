package application

import application.services.*
import infrastructure.repositories.AccountRepository
import infrastructure.repositories.BalanceRepository
import infrastructure.repositories.TransactionRepository
import infrastructure.repositories.config.AppDataSource
import org.koin.dsl.module

val applicationModule = module {
    single { AppDataSource().create() }

    single<AccountService> { AccountServiceImpl(get()) }
    single { AccountRepository(get()) }

    single<TransactionService> { TransactionServiceImpl(get(), get(), get()) }
    single { TransactionRepository(get()) }

    single<BalanceService> { BalanceServiceImpl(get())}
    single { BalanceRepository(get()) }

}

