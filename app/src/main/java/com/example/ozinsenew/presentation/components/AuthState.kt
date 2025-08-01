package com.example.ozinsenew.presentation.components

import com.google.firebase.auth.FirebaseUser

data class AuthState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val currentUser: FirebaseUser? = null,
    val isUserLoggedIn: Boolean = false
)