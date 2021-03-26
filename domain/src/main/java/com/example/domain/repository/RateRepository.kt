package com.example.domain.repository

import com.example.domain.model.RateLocal

interface RateRepository {

    fun getRates(): List<RateLocal>

}