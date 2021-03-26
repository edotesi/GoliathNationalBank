package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    @SerializedName("sku")
    val sku: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)