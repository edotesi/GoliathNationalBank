package com.example.presentation.di

import com.example.domain.repository.RateRepository
import com.example.domain.usecase.Rates
import com.example.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class PresentationModule {

    @Provides
    fun homeViewModelProvider(rates: Rates) = HomeViewModel(rates)

    @Provides
    fun getRatesProvider(ratesRepository: RateRepository) = Rates(ratesRepository)

}