package com.example.ozinsenew.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed interface Screen {
    @Serializable
    data object SplashScreen : Screen
    @Serializable
    data object OnboardingScreen : Screen
    @Serializable
    data object LoginScreen : Screen
}