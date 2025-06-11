package com.example.ozinsenew.presentation.home

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
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozinsenew.R
import com.example.ozinsenew.data.home.BoxData
import com.example.ozinsenew.data.home.boxData
import com.example.ozinsenew.data.home.headBoxData
import com.example.ozinsenew.data.home.middleBoxData
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White

@SuppressLint("UnrememberedMutableInteractionSource")
@Preview
@Composable
fun HomeScreen() {
    val data: BoxData
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = Background,
                elevation = 0.dp,
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .padding(start = 24.dp)
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
                        Cards(item = it)
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
                        Boxes(item = it)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
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
                        HighCard(item = it)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun HighCard(item: BoxData) {
    Column {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(Gray)
                .clickable(
                    onClick = {},
                    indication = ripple(bounded = false),
                    interactionSource = MutableInteractionSource()
                )
        ) {
            Image(
                painterResource(item.image),
                contentDescription = "page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp, 180.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.title,
            style = Typography.bodyMedium,
            color = White
        )
        Text(
            text = item.description,
            style = Typography.bodySmall,
            color = Gray
        )
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun Boxes(item: BoxData) {
    Column {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(Gray)
                .clickable(
                    onClick = {

                    },
                    indication = ripple(bounded = false),
                    interactionSource = MutableInteractionSource()
                )
        ) {
            Image(
                painterResource(item.image),
                contentDescription = "page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(230.dp, 150.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.title,
            style = Typography.bodyMedium,
            color = White
        )
        Text(
            text = item.description,
            style = Typography.bodySmall,
            color = Gray
        )
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun Cards(item: BoxData) {
    Column(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(Gray)
                .clickable(
                    onClick = {

                    },
                    indication = ripple(bounded = false),
                    interactionSource = MutableInteractionSource()
                )
        ) {
            Image(
                painterResource(item.image),
                contentDescription = "page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(340.dp, 200.dp)
            )
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(Pink)
            ) {
                Text(
                    text = "Телехикая",
                    fontSize = 12.sp,
                    color = White,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)

                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.width(340.dp)
        ) {
            Text(
                text = item.title,
                style = Typography.bodyMedium,
                color = White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp,
                letterSpacing = 1.sp,
                color = Gray,
            )
        }
    }
}








