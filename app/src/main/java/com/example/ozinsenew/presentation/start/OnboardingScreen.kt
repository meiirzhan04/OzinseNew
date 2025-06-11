package com.example.ozinsenew.presentation.start

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ozinsenew.data.OnboardingPreferences
import com.example.ozinsenew.data.startTextList
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White
import kotlinx.coroutines.launch

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OnboardingScreen(
    navController: NavController,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val preferences = remember { OnboardingPreferences(context) }
    val pagerState = rememberPagerState { startTextList.size }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        val item = startTextList[index]

        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val screenHeight = maxHeight
            val screenWidth = maxWidth

            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Background, Color.Transparent),
                            startY = screenHeight.value * 1.6f,
                            endY = 0f
                        )
                    )
            )

            if (pagerState.currentPage != startTextList.lastIndex) {
                Text(
                    text = "Өткізу",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 48.dp, end = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF111827))
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clickable {
                            scope.launch {
                                preferences.setOnboardingCompleted()
                                navController.navigate(Screen.LoginScreen) {
                                    popUpTo(Screen.OnboardingScreen) { inclusive = true }
                                }
                            }
                        },
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = screenHeight * 0.1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(screenHeight * 0.6f))

                Text(
                    text = item.title,
                    color = White,
                    style = Typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = item.description,
                    color = Gray,
                    style = Typography.bodySmall,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Indicator(
                    currentPage = pagerState.currentPage,
                    pageCount = startTextList.size
                )

                if (index == startTextList.lastIndex) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            scope.launch {
                                preferences.setOnboardingCompleted()
                                navController.navigate(Screen.LoginScreen) {
                                    popUpTo(Screen.OnboardingScreen) { inclusive = true }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Pink,
                            contentColor = White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Әрі қарай",
                            modifier = Modifier.padding(vertical = 12.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Indicator(
    modifier: Modifier = Modifier,
    currentPage: Int,
    pageCount: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            Box(
                modifier = Modifier
                    .size(
                        width = if (index == currentPage) 24.dp else 8.dp,
                        height = 8.dp
                    )
                    .background(
                        color = if (index == currentPage) Color(0xFFB17DF9) else Color.Gray,
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}
