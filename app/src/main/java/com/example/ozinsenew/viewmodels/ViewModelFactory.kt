package com.example.ozinsenew.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class ViewModelFactory(
    private val application: Application,
    private val firebaseAuth: FirebaseAuth
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application, firebaseAuth) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}