package com.example.ozinsenew.presentation.navigation

import android.annotation.SuppressLint
import android.app.Application
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
import com.example.ozinsenew.presentation.screen.home.BookmarksScreen
import com.example.ozinsenew.presentation.screen.home.DetailScreen
import com.example.ozinsenew.presentation.screen.home.HomeScreen
import com.example.ozinsenew.presentation.screen.home.SearchScreen
import com.example.ozinsenew.presentation.screen.login.LoginScreen
import com.example.ozinsenew.presentation.screen.login.RegisterScreen
import com.example.ozinsenew.presentation.screen.profile.EditProfile
import com.example.ozinsenew.presentation.screen.profile.ProfileScreen
import com.example.ozinsenew.presentation.screen.profile.ResetPasswordScreen
import com.example.ozinsenew.presentation.components.start.OnboardingScreen
import com.example.ozinsenew.presentation.components.start.SplashScreen
import com.example.ozinsenew.presentation.components.video.CustomVideoPlayerScreen
import com.example.ozinsenew.presentation.viewmodels.ListViewModel
import com.example.ozinsenew.presentation.viewmodels.MainViewModel
import com.example.ozinsenew.presentation.viewmodels.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph(
    navController: NavHostController,
    listViewModel: ListViewModel,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current.applicationContext as Application
    val firebaseAuth = FirebaseAuth.getInstance()
    val viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(context, firebaseAuth)
    )
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
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(Screen.RegisterScreen.route()) {
            RegisterScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(Screen.HomeScreen.route()) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
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
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            }
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
                onCategorySelected = {},
                viewModel = viewModel
            )
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
                viewModel = viewModel
            )
        }

        composable(Screen.EditProfile.route()) {
            EditProfile(navController = navController, viewModel)
        }

        composable(Screen.ResetPasswordScreen.route()) {
            ResetPasswordScreen(navController = navController)
        }

        composable(
            route = "video_player?uri={uri}",
            arguments = listOf(
                navArgument("uri") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val uri = backStackEntry.arguments?.getString("uri") ?: return@composable
            CustomVideoPlayerScreen(navController = navController, videoUri = uri)
        }

    }
}