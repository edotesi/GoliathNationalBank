package com.example.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.RateLocal
import com.example.domain.repository.RateRepository
import javax.inject.Inject

class Rates @Inject constructor(private val rateRepository: RateRepository) {

    suspend fun getRates(): MutableLiveData<ArrayList<RateLocal>> {
        return rateRepository.getRates()
    }

}