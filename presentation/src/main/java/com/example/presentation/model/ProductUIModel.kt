package com.example.presentation.model

import java.io.Serializable
import java.util.*

data class ProductUIModel(
    var name: String,
    var amount: String,
    var currency: String,
    var transactions: ArrayList<TransactionUIModel>
) : Serializable