package com.example.ozinsenew.presentation.bottom

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Grey800
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
            val icon = when (screen) {
                is Screen.HomeScreen -> R.drawable.ic_home
                is Screen.SearchScreen -> R.drawable.ic_search
                is Screen.BookmarksScreen -> R.drawable.ic_bookmark_bottom
                is Screen.ProfileScreen -> R.drawable.ic_profile
                else -> R.drawable.ic_home
            }

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = icon),
                        contentDescription = screen::class.simpleName,
                        tint = if (screen.route() == currentScreen) Red300 else Gray
                    )
                },
                selected = currentScreen == screen.route(),
                onClick = { onItemSelected(screen) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Red300,
                    unselectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,

                ),
            )
        }
    }
}
