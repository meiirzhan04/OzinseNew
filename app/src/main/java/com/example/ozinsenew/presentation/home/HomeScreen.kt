package com.example.ozinsenew.presentation.home

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ozinsenew.R
import com.example.ozinsenew.data.home.BoxData
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Grey400
import com.example.ozinsenew.ui.theme.Red500
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.viewmodels.MainViewModel

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MainViewModel) {
    val headBoxData = viewModel.headBoxData
    val middleBoxData = viewModel.middleBoxData
    val boxData = viewModel.boxData

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(bottom = 50.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {
                        Image(
                            painterResource(R.drawable.ic_o),
                            contentDescription = "o",
                        )
                        Image(
                            painterResource(R.drawable.ic_zinse),
                            contentDescription = "zinse",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                LazyRow(
                    contentPadding = PaddingValues(start = 24.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(headBoxData, key = { it.id }) { item ->
                        BoxCard(
                            item = item,
                            onClick = {
                                navController.navigate(Screen.DetailScreen(boxId = item.id).route())
                            },
                            size = DpSize(340.dp, 200.dp),
                            showTag = true
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Қарауды жалғастырыңыз",
                    fontWeight = FontWeight.Bold,
                    style = Typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(start = 24.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    contentPadding = PaddingValues(start = 24.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(middleBoxData, key = { it.id }) { item ->
                        BoxCard(
                            item = item,
                            onClick = {
                                navController.navigate(Screen.DetailScreen(boxId = item.id).route())
                            },
                            size = DpSize(230.dp, 150.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Трендтегілер",
                        style = Typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(
                        onClick = {},
                        interactionSource = MutableInteractionSource(),
                    ) {
                        Text(
                            text = "Барлығы",
                            style = Typography.bodySmall,
                            color = Color(0xFF_B376F7),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    contentPadding = PaddingValues(start = 24.dp, end = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(boxData) {
                        BoxCard(
                            item = it,
                            onClick = {
                                navController.navigate(
                                    Screen.DetailScreen(boxId = it.id).route()
                                )
                            },
                            size = DpSize(120.dp, 180.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun BoxCard(
    item: BoxData,
    size: DpSize,
    showTag: Boolean = false,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = Modifier.width(size.width)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Gray)
                .clickable(
                    onClick = onClick,
                    indication = ripple(bounded = false),
                    interactionSource = interactionSource
                )
        ) {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(size)
            )

            if (showTag) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Red500)
                ) {
                    Text(
                        text = "Телехикая",
                        fontSize = 12.sp,
                        color = White,
                        letterSpacing = 0.5.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = item.title,
            style = Typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.description,
            style = Typography.bodySmall,
            color = Grey400,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


