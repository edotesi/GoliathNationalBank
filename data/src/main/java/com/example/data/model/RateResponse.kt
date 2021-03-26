package com.example.data.model

import com.google.gson.annotations.SerializedName

data class RateResponse(
        @SerializedName("from")
        val from: String,
        @SerializedName("to")
        val to: String,
        @SerializedName("rate")
        val rate: String
)