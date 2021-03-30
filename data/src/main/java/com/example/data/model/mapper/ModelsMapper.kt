package com.example.data.model.mapper

import com.example.data.model.RateResponse
import com.example.data.model.TransactionResponse
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal

/**
 * Method used for convert the rate response to rate local model.
 * @return ArrayList<RateLocal> with all the rates converted to local model.
 */
fun rateResponseToRateLocal(rateResponseList: ArrayList<RateResponse>): ArrayList<RateLocal> {
    var rateLocalList = ArrayList<RateLocal>()

    rateResponseList.forEach { rate ->
        rateLocalList.add(RateLocal(from = rate.from, to = rate.to, rate = rate.rate))
    }
    return rateLocalList
}

/**
 * Method used for convert the transactions response to transaction local model.
 * @return ArrayList<RateLocal> with all the transactions converted to transaction model.
 */
fun transactionResponseToTransactionLocal(transactionResponseList: ArrayList<TransactionResponse>): ArrayList<TransactionLocal> {
    var transactionLocalList = ArrayList<TransactionLocal>()

    transactionResponseList.forEach { transaction ->
        transactionLocalList.add(
            TransactionLocal(
                sku = transaction.sku,
                amount = transaction.amount,
                currency = transaction.currency
            )
        )
    }
    return transactionLocalList
}