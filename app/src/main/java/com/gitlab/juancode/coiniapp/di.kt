package com.gitlab.juancode.coiniapp

import android.app.Application
import com.gitlab.juancode.coiniapp.data.datasource.RemoteDataSource
import com.gitlab.juancode.coiniapp.data.repository.CoiniRepository
import com.gitlab.juancode.coiniapp.data.repository.FlagRepository
import com.gitlab.juancode.coiniapp.data.service.ServiceDataSource
import com.gitlab.juancode.coiniapp.ui.balance.BalanceFragment
import com.gitlab.juancode.coiniapp.ui.balance.BalanceViewModel
import com.gitlab.juancode.coiniapp.ui.contacts.ContactsFragment
import com.gitlab.juancode.coiniapp.ui.contacts.ContactsViewModel
import com.gitlab.juancode.coiniapp.ui.login.LoginFragment
import com.gitlab.juancode.coiniapp.ui.login.LoginViewModel
import com.gitlab.juancode.coiniapp.ui.register.RegisterViewModel
import com.gitlab.juancode.coiniapp.ui.send.SendFragment
import com.gitlab.juancode.coiniapp.ui.send.SendViewModel
import com.gitlab.juancode.coiniapp.ui.transaction.TransactionFragment
import com.gitlab.juancode.coiniapp.ui.transaction.TransactionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
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
    factory { CoiniRepository(get()) }
}

private val registerModule = module {
    viewModel { RegisterViewModel(get(), get()) }
    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel(get()) }
    }

    scope(named<BalanceFragment>()) {
        viewModel { BalanceViewModel(get()) }
    }

    scope(named<SendFragment>()) {
        viewModel { SendViewModel(get()) }
    }

    scope(named<TransactionFragment>()) {
        viewModel { TransactionViewModel(get()) }
    }

    scope(named<ContactsFragment>()) {
        viewModel { ContactsViewModel(get()) }
    }
}