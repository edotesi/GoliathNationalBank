package com.example.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.model.RateResponse
import com.example.data.model.mapper.rateResponseToRateLocal
import com.example.data.service.RatesService
import com.example.domain.model.RateLocal
import com.example.domain.repository.RateRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(private val ratesService: RatesService) :
    RateRepository {
    override fun getRates(): MutableLiveData<ArrayList<RateLocal>> {
        var rates = MutableLiveData<ArrayList<RateLocal>>()
        ratesService.getRates().enqueue(object : Callback<List<RateResponse>> {
            override fun onResponse(
                call: Call<List<RateResponse>>,
                response: Response<List<RateResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        rates.value = rateResponseToRateLocal(list)
                        Log.i("Prubea Repo", rates.value.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<RateResponse>>, t: Throwable) {
            }
        })
        return rates
    }
}