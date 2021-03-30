package com.example.presentation.model

import java.io.Serializable

data class TransactionUIModel(
    var amount: String,
    var currency: String
) : Serializable