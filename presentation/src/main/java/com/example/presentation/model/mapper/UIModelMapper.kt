package com.example.presentation.model.mapper

import com.example.domain.model.TransactionLocal
import com.example.presentation.model.TransactionUIModel

fun transactionLocalToTransactionUI(transactionLocal: TransactionLocal): TransactionUIModel {
    return TransactionUIModel(
        transactionLocal.amount,
        transactionLocal.currency
    )
}