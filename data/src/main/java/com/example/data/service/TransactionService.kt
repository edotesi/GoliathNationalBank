package com.example.data.service

import com.example.data.model.TransactionResponse
import retrofit2.Call
import retrofit2.http.GET

interface TransactionService {

    @GET("transactions.json")
    fun getTransactions(): Call<ArrayList<TransactionResponse>>

}