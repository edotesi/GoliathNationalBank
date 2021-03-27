package com.example.presentation.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.domain.usecase.Rates
import com.example.domain.usecase.Transactions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel @ViewModelInject constructor(
    private val rates: Rates,
    private val transactions: Transactions
) : ViewModel() {

    var ratesList: MutableLiveData<ArrayList<RateLocal>> = MutableLiveData()
    var transactionsList: MutableLiveData<ArrayList<TransactionLocal>> = MutableLiveData()

    init {
        getRates()
        getTransactions()
    }

    private fun getRates() {
        ratesList = rates.getRates()
    }

    private fun getTransactions() {
        transactionsList = transactions.getTransactions()
    }

}