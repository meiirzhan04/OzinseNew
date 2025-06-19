package com.example.ozinsenew.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.presentation.home.BookmarksScreen
import com.example.ozinsenew.presentation.home.DetailScreen
import com.example.ozinsenew.presentation.home.HomeScreen
import com.example.ozinsenew.presentation.home.SearchScreen
import com.example.ozinsenew.presentation.login.LoginScreen
import com.example.ozinsenew.presentation.login.RegisterScreen
import com.example.ozinsenew.presentation.profile.EditProfile
import com.example.ozinsenew.presentation.profile.ProfileScreen
import com.example.ozinsenew.presentation.profile.ResetPasswordScreen
import com.example.ozinsenew.presentation.start.OnboardingScreen
import com.example.ozinsenew.presentation.start.SplashScreen
import com.example.ozinsenew.viewmodels.ListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph(
    navController: NavHostController,
    listViewModel: ListViewModel,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val onBoardingPreferences = OnboardingPreferences(context)


    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route()
    ) {
        composable(Screen.SplashScreen.route()) {
            SplashScreen(
                navController,
                onBoardingPreferences
            )
        }

        composable(Screen.OnboardingScreen.route()) {
            OnboardingScreen(
                navController = navController
            )
        }

        composable(Screen.LoginScreen.route()) {
            LoginScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }

        composable(Screen.RegisterScreen.route()) {
            RegisterScreen(
                viewModel = viewModel(),
                navController = navController
            )
        }

        composable(Screen.HomeScreen.route()) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel()
            )
        }

        composable(
            route = Screen.DetailScreen(0).baseRoute(),
            arguments = listOf(navArgument("boxId") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("boxId")?.let { boxId ->
                DetailScreen(
                    navController = navController,
                    itemId = boxId,
                    listViewModel = listViewModel,
                    viewModel = viewModel(),
                    paddingValues = paddingValues
                )
            }
        }

        composable(Screen.SearchScreen.route()) {
            SearchScreen(navController = navController)
        }

        composable(
            route = Screen.BookmarksScreen.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("category")?.let { category ->
                BookmarksScreen(
                    listViewModel = listViewModel,
                    navController = navController
                )
            }
        }

        composable(Screen.ProfileScreen.route()) {
            ProfileScreen(
                navController = navController,
                viewModel = viewModel()
            )
        }

        composable(Screen.EditProfile.route()) {
            EditProfile(navController = navController, viewModel())
        }

        composable(Screen.ResetPasswordScreen.route()) {
            ResetPasswordScreen(navController = navController)
        }
    }
}