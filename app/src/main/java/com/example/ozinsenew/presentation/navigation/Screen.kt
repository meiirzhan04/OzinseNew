package com.example.ozinsenew.presentation.navigation

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
    object BookmarksScreen : Screen {
        const val route = "bookmark/{category}"
    }

    @Serializable
    object ProfileScreen : Screen

    @Serializable
    object EditProfile : Screen

    @Serializable
    object ResetPasswordScreen : Screen

    @Serializable
    data class CustomVideoPlayer(val videoUri: String) : Screen {
        fun route() = "video_player/$videoUri"
        companion object {
            fun createRoute(videoUri: String): String = "video_player/$videoUri"
        }
    }

}

fun Screen.route(): String = when (this) {
    Screen.SplashScreen -> "splash"
    Screen.OnboardingScreen -> "onboarding"
    Screen.LoginScreen -> "login"
    Screen.RegisterScreen -> "register"
    Screen.HomeScreen -> "home"
    is Screen.DetailScreen -> "detail/${this.boxId}"
    Screen.SearchScreen -> "search"
    Screen.BookmarksScreen -> "bookmark/{category}"
    Screen.ProfileScreen -> "profile"
    Screen.EditProfile -> "edit_profile"
    Screen.ResetPasswordScreen -> "reset_password"
    is Screen.CustomVideoPlayer -> "video_player/$videoUri"
}

fun Screen.baseRoute(): String = when (this) {
    Screen.SplashScreen -> "splash"
    Screen.OnboardingScreen -> "onboarding"
    Screen.LoginScreen -> "login"
    Screen.RegisterScreen -> "register"
    Screen.HomeScreen -> "home"
    is Screen.DetailScreen -> "detail/{boxId}"
    Screen.SearchScreen -> "search"
    Screen.BookmarksScreen -> "bookmark/{category}"
    Screen.ProfileScreen -> "profile"
    Screen.EditProfile -> "edit_profile"
    Screen.ResetPasswordScreen -> "reset_password"
    is Screen.CustomVideoPlayer -> "video_player/$videoUri"
}
