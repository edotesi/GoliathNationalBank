package com.example.presentation.model

import com.example.domain.model.TransactionLocal
import java.util.*

data class ProductUIModel(
    var name: String,
    var amount: String,
    var currency: String,
    var transactions: ArrayList<TransactionUIModel>
)