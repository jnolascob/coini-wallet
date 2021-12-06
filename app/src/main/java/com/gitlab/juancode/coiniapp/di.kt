package com.gitlab.juancode.coiniapp

import android.app.Application
import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.data.repository.FlagRepository
import com.gitlab.juancode.coiniapp.data.service.ServiceDataSource
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, registerModule))
    }
}

private val appModule = module {
    factory<RemoteDataSource> { ServiceDataSource() }
}

private val dataModule = module {
    factory { FlagRepository(get()) }
}

private val registerModule = module {
    viewModel { RegisterViewModel(get()) }

}