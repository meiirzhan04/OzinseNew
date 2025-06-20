package com.example.ozinsenew.data.theme

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "theme_prefs")

object ThemePreference {
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    fun getThemeFlow(context: Context): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[DARK_MODE_KEY] ?: false
        }
    }

    suspend fun setDarkMode(context: Context, isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = isDark
        }
    }
}