package com.example.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.domain.usecase.Rates
import com.example.domain.usecase.Transactions
import com.example.presentation.model.ProductUIModel
import com.example.presentation.utils.getProductsList
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val rates: Rates,
    private val transactions: Transactions
) : ViewModel() {

    var productList: MutableLiveData<ArrayList<ProductUIModel>> =
        MutableLiveData()
    var transactionsList: MutableLiveData<ArrayList<TransactionLocal>> = MutableLiveData()
    var ratesList: MutableLiveData<ArrayList<RateLocal>> = MutableLiveData()
    private val mTransactionsList: ArrayList<TransactionLocal> = ArrayList()
    private val mRatesList: ArrayList<RateLocal> = ArrayList()


    init {
        viewModelScope.launch {
            getRates()
            getTransactions()
        }
    }

    private suspend fun getRates() {
        ratesList = rates.getRates()
    }

    private suspend fun getTransactions() {
        transactionsList = transactions.getTransactions()
    }

    fun getProducts() {
        ratesList.value?.let { rates ->
            transactionsList.value?.let { transactions ->
                productList.postValue(
                    getProductsList(
                        transactions,
                        rates
                    )
                )
            }
        }
    }
}