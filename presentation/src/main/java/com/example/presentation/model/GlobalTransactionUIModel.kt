package com.example.presentation.model

import java.util.*

data class GlobalTransactionUIModel(
    var name: String,
    var amount: Float,
    var currency: String,
    var transactions: ArrayList<TransactionUIModel>?
)