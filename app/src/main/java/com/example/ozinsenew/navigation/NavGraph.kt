package com.example.ozinsenew.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.presentation.start.OnboardingScreen
import com.example.ozinsenew.presentation.start.SplashScreen
import com.example.ozinsenew.presentation.login.LoginScreen
import com.example.ozinsenew.presentation.login.RegisterScreen
import com.example.ozinsenew.viewmodels.ViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val onBoardingPreferences = OnboardingPreferences(LocalContext.current)
    val viewModel: ViewModel = viewModel()

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
            LoginScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }
        composable<Screen.RegisterScreen> {
            RegisterScreen(
                viewModel = viewModel(),
                navController
            )
        }
    }
}