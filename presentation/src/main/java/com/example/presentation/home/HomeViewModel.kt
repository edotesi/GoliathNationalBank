package com.example.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.domain.usecase.Rates
import com.example.domain.usecase.SharedPreferences
import com.example.domain.usecase.Transactions
import com.example.presentation.model.ProductUIModel
import com.example.presentation.utils.getProductsList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val rates: Rates,
    private val transactions: Transactions,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var productList: MutableLiveData<ArrayList<ProductUIModel>> =
        MutableLiveData()
    var transactionsList: MutableLiveData<ArrayList<TransactionLocal>> = MutableLiveData()
    var ratesList: MutableLiveData<ArrayList<RateLocal>> = MutableLiveData()
    var userCurrency: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            sharedPreferences.setDefaultUserCurrency()
            getRates()
            getUserCurrency()
            getTransactions()
        }
    }

    private suspend fun getRates() {
        ratesList = rates.getRates()
    }

    private suspend fun getTransactions() {
        transactionsList = transactions.getTransactions()
    }

    private fun getUserCurrency() {
        userCurrency.postValue(sharedPreferences.getUserCurrency())
        //TODO: Improve method. When user selects new currency they need to reset the app to apply changes on recycler.
    }

    fun getProducts() {
        ratesList.value?.let { rates ->
            transactionsList.value?.let { transactions ->
                GlobalScope.launch {
                    productList.postValue(
                        getProductsList(
                            transactions,
                            rates,
                            userCurrency.value.toString()
                        )
                    )
                }
            }
        }
    }
}