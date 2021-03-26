package com.example.data.model.mapper

import com.example.data.model.RateResponse
import com.example.domain.model.RateLocal

fun rateResponseToRateLocal(rateResponseList: ArrayList<RateResponse>): ArrayList<RateLocal> {
    var rateLocalList = ArrayList<RateLocal>()

    rateResponseList.forEach { rate ->
        rateLocalList.add(RateLocal(from = rate.from, to = rate.to, rate = rate.rate))
    }
    return rateLocalList
}