package com.example.ozinsenew.data.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ozinsenew.navigation.Screen

data class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector
)


val bottomNavItems = listOf(
    BottomNavItem(Screen.HomeScreen, Icons.Default.Home),
    BottomNavItem(Screen.SearchScreen, Icons.Default.Search),
    BottomNavItem(Screen.BookmarksScreen, Icons.Default.LocationOn),
    BottomNavItem(Screen.ProfileScreen, Icons.Default.Person)
)
