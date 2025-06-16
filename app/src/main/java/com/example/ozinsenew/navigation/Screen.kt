package com.example.ozinsenew.navigation

import kotlinx.serialization.Serializable


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

    @Serializable
    object SearchScreen : Screen

    @Serializable
    object BookmarksScreen : Screen

    @Serializable
    object ProfileScreen : Screen

    @Serializable
    object EditProfile : Screen
}
