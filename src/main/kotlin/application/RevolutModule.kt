package application

import infrastructure.repositories.AccountRepository
import infrastructure.repositories.AppDatasource
import org.koin.dsl.module

val revolutModule = module {
    single<AccountService> { AccountServiceImpl(get())}
    single { AccountRepository(get()) }
    single { AppDatasource().create() }
}