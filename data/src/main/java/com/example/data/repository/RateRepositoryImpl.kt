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
import kotlin.coroutines.coroutineContext

class RateRepositoryImpl @Inject constructor(private val ratesService: RatesService) :
    RateRepository {

    /**
     * Async method used for get rates from service using a call with Retrofit instance.
     * @return MutableLiveData<ArrayList<RateLocal>> with all the rates for use it on viewModel
     */
    override suspend fun getRates(): MutableLiveData<ArrayList<RateLocal>> {
        var rates = MutableLiveData<ArrayList<RateLocal>>()
        ratesService.getRates().enqueue(object : Callback<ArrayList<RateResponse>> {
            override fun onFailure(call: Call<ArrayList<RateResponse>>, t: Throwable) {
                Log.i("Error on getRates", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<RateResponse>>,
                response: Response<ArrayList<RateResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { list ->
                        rates.postValue(rateResponseToRateLocal(list))
                    }
                }
            }
        })
        return rates
    }
}