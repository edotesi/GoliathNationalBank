package com.example.domain.usecase

import com.example.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferences @Inject constructor(private val sharedPreferencesRepository: SharedPreferencesRepository) {

    /**
     * Method of useCase used to get all the transactions from service
     * @return MutableLiveData<ArrayList<TransactionLocal>> with all the data ready to use on the viewModel
     */
    fun getUserCurrency(): String {
        return sharedPreferencesRepository.getUserCurrency()
    }

    fun setDefaultUserCurrency() {
        sharedPreferencesRepository.setDefaultUserCurrency()
    }
}