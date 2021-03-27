package com.example.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.TransactionLocal

interface TransactionRepository {

    fun getTransactions(): MutableLiveData<ArrayList<TransactionLocal>>

}