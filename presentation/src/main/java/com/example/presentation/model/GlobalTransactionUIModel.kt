package com.example.presentation.model

import java.util.*

data class GlobalTransactionUIModel(
    var name: String,
    var amount: String,
    var currency: String,
    var transactions: ArrayList<TransactionUIModel>?
)