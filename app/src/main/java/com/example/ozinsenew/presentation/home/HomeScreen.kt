package com.example.ozinsenew.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozinsenew.R
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White

@Preview
@Composable
fun HomeScreen() {
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
                .padding(innerPadding)
                .background(Background)
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
                    items(images) {
                        Cards(image = it)
                        Cards(image = it)
                    }
                }
            }
        }
    }
}

val images = listOf(
    R.drawable.ic_page_1,
)

@Composable
fun Cards(image: Int) {
    Column(
        modifier = Modifier
            .padding(start = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(Gray)
                .clickable(
                    onClick = {}
                )
        ) {
            Image(
                painterResource(image),
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
                text = "Қызғалдақтар мекені",
                style = Typography.bodyMedium,
                color = White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Шытырман оқиғалы мультсериал Елбасының «Ұлы даланың жеті қыры» бағдарламасы аясында жүз...",
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp,
                letterSpacing = 1.sp,
                color = Gray,
            )
        }
    }
}








