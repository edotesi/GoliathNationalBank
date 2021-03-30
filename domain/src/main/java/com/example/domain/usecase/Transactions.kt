package com.example.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.TransactionLocal
import com.example.domain.repository.TransactionRepository
import javax.inject.Inject

class Transactions @Inject constructor(private val transactionRepository: TransactionRepository) {

    /**
     * Method of useCase used to get all the transactions from service
     * @return MutableLiveData<ArrayList<TransactionLocal>> with all the data ready to use on the viewModel
     */
    suspend fun getTransactions(): MutableLiveData<ArrayList<TransactionLocal>>{
        return transactionRepository.getTransactions()
    }

}