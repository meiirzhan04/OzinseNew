package com.example.ozinsenew.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class OnboardingPreferences(private val context: Context) {
    private val onBoardingKey = booleanPreferencesKey("onboarding_completed")

    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[onBoardingKey] == true }

    suspend fun setOnboardingCompleted() {
        context.dataStore.edit { preferences -> preferences[onBoardingKey] = true }
    }
}
