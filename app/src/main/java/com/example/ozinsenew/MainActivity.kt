package com.example.ozinsenew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ozinsenew.data.home.BoxData
import com.example.ozinsenew.navigation.MainScreen
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.ui.theme.OzinseNewTheme
import com.example.ozinsenew.viewmodels.ListViewModel
import com.example.ozinsenew.viewmodels.ViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            val viewModel: ViewModel by viewModels()
            val lists: BoxData = viewModel.headBoxData[0]
            OzinseNewTheme(
                darkTheme = viewModel.isDarkTheme
            ) {
                val listViewModel: ListViewModel = viewModel()
                val item = ListItems(
                    id = lists.id,
                    name = lists.title,
                    data = lists.description,
                    image = lists.image,
                    category = lists.category,
                )
                MainScreen(
                    item = item,
                    listViewModel = listViewModel
                )
            }
        }
    }
}