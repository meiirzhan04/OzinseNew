package com.example.ozinsenew.presentation.home

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ozinsenew.R
import com.example.ozinsenew.data.home.BoxData
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White
import com.example.ozinsenew.viewmodels.ViewModel

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun HomeScreen(navController: NavHostController, viewModel: ViewModel) {
    val headBoxData = viewModel.headBoxData
    val middleBoxData = viewModel.middleBoxData
    val boxData = viewModel.boxData

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
                .padding(bottom = 65.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .background(BoxGray)
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
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                LazyRow {
                    items(headBoxData) {
                        Spacer(modifier = Modifier.width(24.dp))
                        BoxCard(
                            item = it,
                            onClick = {
                                navController.navigate(
                                    Screen.DetailScreen(boxId = it.id).route()
                                )
                            },
                            size = androidx.compose.ui.unit.DpSize(340.dp, 200.dp),
                            showTag = true
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Қарауды жалғастырыңыз",
                    style = Typography.bodyLarge,
                    color = White,
                    modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(middleBoxData) {
                        Spacer(modifier = Modifier.width(24.dp))
                        BoxCard(
                            item = it,
                            onClick = {
                                navController.navigate(
                                    Screen.DetailScreen(boxId = it.id).route()
                                )
                            },
                            size = androidx.compose.ui.unit.DpSize(230.dp, 150.dp),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Трендтегілер",
                        style = Typography.bodyLarge,
                        color = White,
                    )
                    TextButton(
                        onClick = {},
                        interactionSource = MutableInteractionSource(),
                    ) {
                        Text(
                            text = "Барлығы",
                            style = Typography.bodySmall,
                            color = Pink,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(boxData) {
                        Spacer(modifier = Modifier.width(24.dp))
                        BoxCard(
                            item = it,
                            onClick = {
                                navController.navigate(
                                    Screen.DetailScreen(boxId = it.id).route()
                                )
                            },
                            size = androidx.compose.ui.unit.DpSize(120.dp, 180.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun BoxCard(
    item: BoxData,
    size: androidx.compose.ui.unit.DpSize,
    showTag: Boolean = false,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.width(size.width)) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Gray)
                .clickable(
                    onClick = onClick,
                    indication = ripple(bounded = false),
                    interactionSource = MutableInteractionSource()
                )
        ) {
            Image(
                painterResource(item.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(size)
            )
            if (showTag) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Pink)
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
            color = White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.description,
            style = Typography.bodySmall,
            color = Gray,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}



