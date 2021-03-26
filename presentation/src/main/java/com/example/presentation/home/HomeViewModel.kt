package com.example.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.RateLocal
import com.example.domain.usecase.Rates
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val rates: Rates
) : ViewModel() {

    //var ratesList: MutableLiveData<ArrayList<RateLocal>> = MutableLiveData()

    fun getRates() {
        GlobalScope.launch {
           rates.getRates()

        }

    }

}