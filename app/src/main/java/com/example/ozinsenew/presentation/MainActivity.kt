package com.example.ozinsenew.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ozinsenew.data.theme.ThemeViewModel
import com.example.ozinsenew.presentation.navigation.MainScreen
import com.example.ozinsenew.presentation.ui.theme.OzinseNewTheme
import com.example.ozinsenew.presentation.viewmodels.ListViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDark = themeViewModel.isDarkMode.collectAsState().value
            OzinseNewTheme(darkTheme = isDark) {
                val listViewModel: ListViewModel = viewModel()
                MainScreen(
                    listViewModel = listViewModel
                )
            }
        }
    }
}