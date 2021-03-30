package com.example.data.repository

import android.content.Context
import com.example.domain.repository.SharedPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    SharedPreferencesRepository {

    private val sharedPreferences =
        context.getSharedPreferences("SESSION_PREFERENCES", Context.MODE_PRIVATE)

    override fun getUserCurrency(): String {
        return sharedPreferences.getString("currency_preference", "") ?: "EUR"
    }

    override fun setDefaultUserCurrency() {
        if (!sharedPreferences.contains("currency_preference")) {
            sharedPreferences.edit().putString("currency_preference", "EUR").apply()
        }
    }
}