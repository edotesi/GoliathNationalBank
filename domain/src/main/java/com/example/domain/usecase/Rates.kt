package com.example.domain.usecase

import com.example.domain.repository.RateRepository
import javax.inject.Inject

class Rates @Inject constructor(private val rateRepository: RateRepository) {

    suspend fun getRates() {
        rateRepository.getRates()
    }

}