package com.example.ozinsenew.presentation.bottom

import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
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
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink


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
        containerColor = BoxGray,
        modifier = Modifier.height(90.dp)
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
                        tint = if (screen.route() == currentScreen) Pink else Gray
                    )
                },
                selected = currentScreen == screen.route(),
                onClick = { onItemSelected(screen) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Pink,
                    unselectedIconColor = Gray,
                    indicatorColor = BoxGray
                ),
            )
        }
    }
}
