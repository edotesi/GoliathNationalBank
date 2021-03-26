package com.example.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.domain.model.RateLocal

interface RateRepository {

    fun getRates(): MutableLiveData<ArrayList<RateLocal>>

}