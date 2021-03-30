package com.example.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.RateLocal
import com.example.domain.repository.RateRepository
import javax.inject.Inject

class Rates @Inject constructor(private val rateRepository: RateRepository) {

    /**
     * Method of useCase used to get all the rates from service
     * @return MutableLiveData<ArrayList<RateLocal>> with all the data ready to use on the viewModel
     */
    suspend fun getRates(): MutableLiveData<ArrayList<RateLocal>> {
        return rateRepository.getRates()
    }

}