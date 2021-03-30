package com.example.presentation.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.presentation.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setSharedPreferences()
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    private fun setSharedPreferences() {
        val preferenceManager = preferenceManager
        preferenceManager.sharedPreferencesName = "SESSION_PREFERENCES"
        preferenceManager.sharedPreferencesMode = Context.MODE_PRIVATE
    }

}