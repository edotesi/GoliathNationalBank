package com.example.presentation.model

import java.util.*

data class GlobalTransactionUIModel(
    var name: String,
    var amount: Long,
    var currency: String,
    var transactions: ArrayList<TransactionUIModel>?
)