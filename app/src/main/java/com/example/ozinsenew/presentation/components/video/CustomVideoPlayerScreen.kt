package com.example.ozinsenew.presentation.components.video

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun CustomVideoPlayerScreen(
    navController: NavController,
    videoUri: String
) {
    val context = LocalContext.current
    val activity = context as? Activity

    LaunchedEffect(Unit) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    DisposableEffect(Unit) {
        onDispose {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    CustomVideoPlayer(
        context = context,
        videoUrl = videoUri,
        onBack = {
            navController.popBackStack()
        }
    )
}

