package com.example.ozinsenew.navigation

import kotlinx.serialization.Serializable
import okhttp3.Route


sealed interface Screen {
    @Serializable
    object SplashScreen : Screen

    @Serializable
    object OnboardingScreen : Screen

    @Serializable
    object LoginScreen : Screen

    @Serializable
    object RegisterScreen : Screen

    @Serializable
    object HomeScreen : Screen

    @Serializable
    data class DetailScreen(val boxId: Int) : Screen
}
