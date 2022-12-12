package com.example.data.service

import com.example.data.model.RateResponse
import retrofit2.Call
import retrofit2.http.GET

interface RatesService {

    @GET("rates")
    fun getRates(): Call<ArrayList<RateResponse>>

}