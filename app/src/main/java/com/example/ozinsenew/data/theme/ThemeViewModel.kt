package com.example.ozinsenew.data.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    init {
        ThemePreference.getThemeFlow(application).onEach {
            _isDarkMode.value = it
        }.launchIn(viewModelScope)
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkMode.value
            ThemePreference.setDarkMode(getApplication(), newTheme)
        }
    }
}