package com.example.ozinsenew.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.presentation.bottom.BottomNavigationBar
import com.example.ozinsenew.presentation.home.BookmarksScreen
import com.example.ozinsenew.presentation.home.DetailScreen
import com.example.ozinsenew.presentation.home.HomeScreen
import com.example.ozinsenew.presentation.home.SearchScreen
import com.example.ozinsenew.presentation.login.LoginScreen
import com.example.ozinsenew.presentation.login.RegisterScreen
import com.example.ozinsenew.presentation.profile.EditProfile
import com.example.ozinsenew.presentation.profile.ProfileScreen
import com.example.ozinsenew.presentation.start.OnboardingScreen
import com.example.ozinsenew.presentation.start.SplashScreen
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.viewmodels.ListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph(
    item: ListItems,
    listViewModel: ListViewModel
) {
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
        composable<Screen.HomeScreen> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(currentScreen = Screen.HomeScreen) { selectedScreen ->
                        navController.navigate(selectedScreen)
                    }
                }
            ) {
                HomeScreen(navController, viewModel())
            }
        }
        composable<Screen.DetailScreen> { backStackEntry ->
            val boxId = backStackEntry.arguments?.getInt("boxId") ?: return@composable
            DetailScreen(
                navController = navController,
                viewModel = viewModel(),
                itemId = boxId,
                item = item,
                listViewModel = listViewModel
            )
        }
        composable<Screen.SearchScreen> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(currentScreen = Screen.SearchScreen) { selectedScreen ->
                        navController.navigate(selectedScreen)
                    }
                }
            ) {
                SearchScreen(
                    navController
                )
            }
        }

        composable<Screen.BookmarksScreen> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(currentScreen = Screen.BookmarksScreen) { selectedScreen ->
                        navController.navigate(selectedScreen)
                    }
                }
            ) {
                BookmarksScreen(item)
            }
        }

        composable<Screen.ProfileScreen> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(currentScreen = Screen.ProfileScreen) { selectedScreen ->
                        navController.navigate(selectedScreen)
                    }
                }
            ) {
                ProfileScreen(navController, viewModel())
            }
        }

        composable<Screen.EditProfile> {
            EditProfile(navController)

        }
    }
}