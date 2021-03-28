package com.example.data.model.mapper

import com.example.data.model.RateResponse
import com.example.data.model.TransactionResponse
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal

fun rateResponseToRateLocal(rateResponseList: ArrayList<RateResponse>): ArrayList<RateLocal> {
    var rateLocalList = ArrayList<RateLocal>()

    rateResponseList.forEach { rate ->
        rateLocalList.add(RateLocal(from = rate.from, to = rate.to, rate = rate.rate))
    }
    return rateLocalList
}

fun transactionResponseToTransactionLocal(transactionResponseList: ArrayList<TransactionResponse>): ArrayList<TransactionLocal> {
    var transactionLocalList = ArrayList<TransactionLocal>()

    transactionResponseList.forEach { transaction ->
        transactionLocalList.add(
            TransactionLocal(
                sku = transaction.sku,
                amount = transaction.amount.toFloat(),
                currency = transaction.currency
            )
        )
    }
    return transactionLocalList
}