package com.example.presentation.di

import com.example.domain.repository.RateRepository
import com.example.domain.repository.TransactionRepository
import com.example.domain.usecase.Rates
import com.example.domain.usecase.Transactions
import com.example.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object PresentationModule {

    @Provides
    fun homeViewModelProvider(rates: Rates, transactions: Transactions) = HomeViewModel(rates, transactions)

    @Provides
    fun getRatesProvider(ratesRepository: RateRepository) = Rates(ratesRepository)

    @Provides
    fun getTransactionsProvider(transactionRepository: TransactionRepository) = Transactions(transactionRepository)

}