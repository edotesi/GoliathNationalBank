package com.example.presentation.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.RateLocal
import com.example.domain.usecase.Rates
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel @ViewModelInject constructor(
    private val rates: Rates
) : ViewModel() {

    var ratesList: MutableLiveData<ArrayList<RateLocal>> = MutableLiveData()

    init {
        getRates()
    }

    private fun getRates() {
        GlobalScope.launch {
            ratesList = rates.getRates()
        }
    }

}