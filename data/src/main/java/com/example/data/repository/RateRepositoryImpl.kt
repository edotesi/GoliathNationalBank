package com.example.data.repository

import android.util.Log
import com.example.data.model.RateResponse
import com.example.data.service.RatesService
import com.example.domain.model.RateLocal
import com.example.domain.repository.RateRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(private val ratesService: RatesService) : RateRepository {
    override fun getRates(): List<RateLocal> {
        var rates = ArrayList<RateLocal>()
        ratesService.getRates().enqueue( object : Callback<List<RateResponse>>{
            override fun onResponse(
                call: Call<List<RateResponse>>,
                response: Response<List<RateResponse>>
            ) {
                Log.i("Ok",  response.body().toString())
                if(response.isSuccessful) {
                    response.body()?.let { list ->
                        rates = list as ArrayList<RateLocal>
                    }
                }
            }
            override fun onFailure(call: Call<List<RateResponse>>, t: Throwable) {
                Log.i("Ko", t.message.toString())
            }
        })
        return rates
    }
}