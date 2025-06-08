package com.example.ozinsenew.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController, onBoardingPreferences: OnboardingPreferences) {
    val coroutineScope = rememberCoroutineScope()
    var isOnboardingCompleted by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(3000)
            isOnboardingCompleted = onBoardingPreferences.isOnboardingCompleted.first()
            navController.navigate(if (isOnboardingCompleted == true) Screen.LoginScreen else Screen.OnboardingScreen) {
                popUpTo(Screen.SplashScreen) { inclusive = true }
            }
        }
    }
    Box {
        Image(
            painterResource(id = R.drawable.ic_splash),
            contentDescription = "splash",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painterResource(id = R.drawable.ic_title),
            contentDescription = "title",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}