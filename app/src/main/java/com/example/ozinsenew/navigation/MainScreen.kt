package com.example.ozinsenew.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ozinsenew.presentation.bottom.BottomNavigationBar
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.viewmodels.ListViewModel

@Composable
fun MainScreen(
    listViewModel: ListViewModel
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.HomeScreen.route(),
                    Screen.SearchScreen.route(),
                    Screen.BookmarksScreen.route(),
                    Screen.ProfileScreen.route()
                )
            ) {
                BottomNavigationBar(currentScreen = currentRoute ?: "") { screen ->
                    navController.navigate(screen.route()) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            listViewModel = listViewModel,
            paddingValues = paddingValues
        )
    }
}