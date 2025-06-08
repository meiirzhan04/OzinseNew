package com.example.ozinsenew.navigation

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.data.startTextList
import com.example.ozinsenew.presentation.home.OnboardingScreen
import com.example.ozinsenew.presentation.home.SplashScreen
import com.example.ozinsenew.presentation.login.LoginScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val onBoardingPreferences = OnboardingPreferences(LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen
    ) {
        composable<Screen.SplashScreen> {
            SplashScreen(navController, onBoardingPreferences)
        }
        composable<Screen.OnboardingScreen> {
            OnboardingScreen(
                navController = navController,
                onClick = { navController.navigate(Screen.SplashScreen) }
            )
        }
        composable<Screen.LoginScreen> {
            LoginScreen()
        }
    }
}