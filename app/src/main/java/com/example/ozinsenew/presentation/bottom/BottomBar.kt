package com.example.ozinsenew.presentation.bottom

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Red300


@Composable
fun BottomNavigationBar(
    currentScreen: String,
    onItemSelected: (Screen) -> Unit
) {
    val items = listOf(
        Screen.HomeScreen,
        Screen.SearchScreen,
        Screen.BookmarksScreen,
        Screen.ProfileScreen
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = Modifier.height(80.dp)
    ) {
        items.forEach { screen ->
            val iconResId = when (screen) {
                is Screen.HomeScreen -> R.drawable.ic_home
                is Screen.SearchScreen -> R.drawable.ic_search
                is Screen.BookmarksScreen -> R.drawable.ic_bookmark_bottom
                is Screen.ProfileScreen -> R.drawable.ic_profile
                else -> R.drawable.ic_home
            }

            val route = screen.route()
            val selected = currentScreen == route

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = screen::class.simpleName
                    )
                },
                selected = selected,
                onClick = {
                    if (!selected) onItemSelected(screen)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Red300,
                    unselectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
            )
        }
    }
}
