package com.example.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.data.model.TransactionResponse
import com.example.data.model.mapper.transactionResponseToTransactionLocal
import com.example.data.service.TransactionService
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.domain.repository.TransactionRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionService: TransactionService) :
    TransactionRepository {

    override fun getTransactions(): MutableLiveData<ArrayList<TransactionLocal>> {
        var transactions = MutableLiveData<ArrayList<TransactionLocal>>()
        transactionService.getTransactions().enqueue(
            object : Callback<ArrayList<TransactionResponse>> {
                override fun onFailure(call: Call<ArrayList<TransactionResponse>>, t: Throwable) {
                    Log.i("Error", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ArrayList<TransactionResponse>>,
                    response: Response<ArrayList<TransactionResponse>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { list ->
                            transactions.postValue(transactionResponseToTransactionLocal(list))
                        }
                    }
                }
            }
        )
        return transactions
    }

}