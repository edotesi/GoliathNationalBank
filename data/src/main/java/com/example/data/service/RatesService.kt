package com.example.data.service

import com.example.data.model.RateResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface RatesService {

    @GET("rates.json")
    fun getRates(): Call<List<RateResponse>>

}