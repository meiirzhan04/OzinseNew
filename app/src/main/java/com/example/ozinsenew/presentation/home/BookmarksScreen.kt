package com.example.ozinsenew.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.TextPink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White
import com.example.ozinsenew.viewmodels.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    listViewModel: ListViewModel,
    category: String,
    navController: NavHostController
) {
    val itemsList by listViewModel.allItems.collectAsState(initial = emptyList())
    val bookmarks by listViewModel
        .getItemsByCategory(category)
        .collectAsState(initial = emptyList())
    Log.d("BookmarkScreen", "Bookmarks count: ${bookmarks.size}")


    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Тізім",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = Typography.bodyLarge,
                            color = White
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Background),
                    modifier = Modifier
                        .background(Background)
                        .padding(horizontal = 24.dp),
                )
                HorizontalDivider(
                    color = BoxGray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            itemsIndexed(itemsList) { index, item ->
                ImageBox(
                    image = item.image,
                    title = item.name,
                    data = item.data,
                    onClick = {
                        navController.navigate(Screen.DetailScreen(item.id).route())
                    }
                )
                if (index != bookmarks.lastIndex) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(
                        color = BoxGray,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}


@Composable
fun ImageBox(
    image: Int,
    title: String,
    data: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(80.dp, 110.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxSize()
            )
        }
        Column {
            Text(
                text = title,
                style = Typography.bodySmall,
                color = White
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = data,
                style = Typography.labelSmall,
                color = White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(BoxGray)
                    .clickable(
                        onClick = onClick
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 1.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_playsmall),
                        contentDescription = ""
                    )
                    Text(
                        text = "Қарау",
                        color = TextPink,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}