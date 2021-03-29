package com.example.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.TransactionLocal
import com.example.domain.repository.TransactionRepository
import javax.inject.Inject

class Transactions @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend fun getTransactions(): MutableLiveData<ArrayList<TransactionLocal>>{
        return transactionRepository.getTransactions()
    }

}