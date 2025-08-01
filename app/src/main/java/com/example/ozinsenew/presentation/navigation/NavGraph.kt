package com.example.ozinsenew.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.presentation.components.start.OnboardingScreen
import com.example.ozinsenew.presentation.components.start.SplashScreen
import com.example.ozinsenew.presentation.components.video.CustomVideoPlayerScreen
import com.example.ozinsenew.presentation.screen.home.BookmarksScreen
import com.example.ozinsenew.presentation.screen.home.DetailScreen
import com.example.ozinsenew.presentation.screen.home.HomeScreen
import com.example.ozinsenew.presentation.screen.home.SearchScreen
import com.example.ozinsenew.presentation.screen.login.LoginScreen
import com.example.ozinsenew.presentation.screen.login.RegisterScreen
import com.example.ozinsenew.presentation.screen.profile.EditProfile
import com.example.ozinsenew.presentation.screen.profile.ProfileScreen
import com.example.ozinsenew.presentation.screen.profile.ResetPasswordScreen
import com.example.ozinsenew.presentation.viewmodels.AuthViewModel
import com.example.ozinsenew.presentation.viewmodels.ListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph(
    navController: NavHostController,
    listViewModel: ListViewModel,
    paddingValues: PaddingValues
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState = authViewModel.state.collectAsState()
    val onboardingPreferences = OnboardingPreferences(navController.context)
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    LaunchedEffect(isAuthenticated) {
        if (!isAuthenticated) {
            navController.navigate(Screen.LoginScreen.route()) {
                popUpTo(0)
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route()
    ) {
        composable(Screen.SplashScreen.route()) {
            SplashScreen(
                navController = navController,
                currentUser = authState.value.currentUser,
                onboardingPreferences = onboardingPreferences

            )
        }
        composable(Screen.OnboardingScreen.route()) {
            OnboardingScreen(
                navController = navController,
            )
        }

        composable(Screen.LoginScreen.route()) {
            LoginScreen(
                viewModel = authViewModel,
                navController = navController
            )
        }
        composable(Screen.RegisterScreen.route()) {
            RegisterScreen(
                viewModel = authViewModel,
                navController = navController
            )
        }

        composable(Screen.HomeScreen.route()) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(
            route = Screen.DetailScreen(0).baseRoute(),
            arguments = listOf(navArgument("boxId") { type = NavType.IntType })
        ) { backStackEntry ->
            val boxId = backStackEntry.arguments?.getInt("boxId") ?: return@composable
            DetailScreen(
                navController = navController,
                itemId = boxId,
                listViewModel = listViewModel,
                paddingValues = paddingValues,
            )
        }
        composable(Screen.SearchScreen.route()) {
            SearchScreen(
                navController = navController,
                categories = listOf(
                    "Телехикая",
                    "Ситком",
                    "Көркем фильм",
                    "Мультфильм",
                    "Мультсериал",
                    "Аниме",
                    "Тв-бағдарлама және реалити-шоу"
                ),
                selectedCategory = null,
                onCategorySelected = {}
            )
        }
        composable(
            route = Screen.BookmarksScreen.route,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            BookmarksScreen(
                listViewModel = listViewModel,
                navController = navController
            )
        }
        composable(Screen.ProfileScreen.route()) {
            ProfileScreen(
                navController = navController,
            )
        }
        composable(Screen.EditProfile.route()) {
            EditProfile(navController = navController)
        }
        composable(Screen.ResetPasswordScreen.route()) {
            ResetPasswordScreen(navController = navController)
        }

        composable(
            route = "video_player?uri={uri}",
            arguments = listOf(navArgument("uri") { type = NavType.StringType })
        ) { backStackEntry ->
            val uri = backStackEntry.arguments?.getString("uri") ?: return@composable
            CustomVideoPlayerScreen(navController = navController, videoUri = uri)
        }
    }
}