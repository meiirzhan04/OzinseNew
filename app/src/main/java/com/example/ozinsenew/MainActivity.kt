package com.example.ozinsenew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ozinsenew.navigation.MainScreen
import com.example.ozinsenew.ui.theme.OzinseNewTheme
import com.example.ozinsenew.viewmodels.ListViewModel
import com.example.ozinsenew.viewmodels.ViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            val viewModel: ViewModel by viewModels()
            OzinseNewTheme(
                darkTheme = viewModel.isDarkTheme
            ) {
                val listViewModel: ListViewModel = viewModel()
                MainScreen(
                    listViewModel = listViewModel
                )
            }
        }
    }
}