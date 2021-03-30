package com.example.domain.repository

interface SharedPreferencesRepository {

    fun getUserCurrency(): String

    fun setDefaultUserCurrency()

}