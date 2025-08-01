package com.example.ozinsenew.presentation.components.start

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.presentation.navigation.Screen
import com.example.ozinsenew.presentation.navigation.route
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    navController: NavController,
    onboardingPreferences: OnboardingPreferences,
    currentUser: FirebaseUser?
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        visible = false
        delay(300)

        val isOnboardingCompleted = onboardingPreferences.isOnboardingCompleted.first()

        when {
            !isOnboardingCompleted -> {
                navController.navigate(Screen.OnboardingScreen.route()) {
                    popUpTo(Screen.SplashScreen.route()) { inclusive = true }
                }
            }

            currentUser != null -> {
                navController.navigate(Screen.HomeScreen.route()) {
                    popUpTo(Screen.SplashScreen.route()) { inclusive = true }
                }
            }

            else -> {
                navController.navigate(Screen.LoginScreen.route()) {
                    popUpTo(Screen.SplashScreen.route()) { inclusive = true }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = visible,
        exit = fadeOut()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = "splash",
                modifier = Modifier.fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.ic_title),
                contentDescription = "title",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}