package com.example.ozinsenew.presentation.screen.home

import android.annotation.SuppressLint
import android.net.Uri
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.ripple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.data.room.bookmark.ListItems
import com.example.ozinsenew.presentation.ui.theme.Grey300
import com.example.ozinsenew.presentation.ui.theme.Grey400
import com.example.ozinsenew.presentation.ui.theme.Grey600
import com.example.ozinsenew.presentation.ui.theme.Red300
import com.example.ozinsenew.presentation.ui.theme.Typography
import com.example.ozinsenew.presentation.viewmodels.ListViewModel
import com.example.ozinsenew.presentation.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DetailScreen(
    navController: NavController,
    itemId: Int,
    viewModel: MainViewModel,
    listViewModel: ListViewModel,
    paddingValues: PaddingValues
) {
    val scope = rememberCoroutineScope()
    val isBookmarked = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current

    val gradientColors = listOf(
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0f),
    )

    val box = viewModel.getBoxById(itemId)

    box?.let {
        LaunchedEffect(itemId) {
            val currentItem = ListItems(
                name = it.title,
                image = it.image,
                data = it.description,
                category = it.category
            )
            isBookmarked.value = listViewModel.isBookmarked(currentItem)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(it.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(start = 24.dp, top = 36.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElementBox(
                    image = if (isBookmarked.value) R.drawable.ic_bookmark_pink else R.drawable.ic_bookmark,
                    text = "Тізімге қосу",
                    onClick = {
                        scope.launch {
                            val item = ListItems(
                                name = it.title,
                                image = it.image,
                                data = it.description,
                                category = it.category
                            )
                            if (isBookmarked.value) {
                                listViewModel.delete(item)
                            } else {
                                listViewModel.insert(item)
                            }
                            isBookmarked.value = !isBookmarked.value
                        }
                    }
                )
                ElementBox(image = R.drawable.ic_play, text = "") {
                    val videoUri =
                        Uri.encode("android.resource://${context.packageName}/${R.raw.sdudentday}")
                    navController.navigate("video_player?uri=$videoUri")
                }
                ElementBox(image = R.drawable.ic_share, text = "Бөлісу")
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 320.dp)
                    .padding(paddingValues)
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            ) {
                item {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = it.title,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "2020 • Телехикая • 5 сезон, 46 серия, 7 мин.",
                            color = Grey400,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            thickness = 1.dp,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                        GradientText(it.description, gradientColors)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Толығырақ",
                            color = Red300,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            modifier = Modifier.clickable(
                                indication = ripple(bounded = true),
                                interactionSource = interactionSource,
                                onClick = {}
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row {
                            Text(
                                text = "Режиссер: ",
                                color = Grey600,
                                fontSize = 12.sp
                            )
                            Text(
                                text = "  Бақдәулет Әлімбеков",
                                color = Grey400,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "Продюсер: ",
                                color = Grey600,
                                fontSize = 12.sp
                            )
                            Text(
                                text = " Сандуғаш Кенжебаева",
                                color = Grey400,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            thickness = 1.dp,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Бөлімдер",
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "5 сезон, 46 серия",
                                    color = Grey400
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Red300
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ElementBox(image: Int, text: String, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.clickable(
            onClick = onClick,
            indication = ripple(bounded = false),
            interactionSource = null
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painterResource(image),
            contentDescription = "",
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Grey300,
            fontWeight = FontWeight.W600
        )
    }
}

@Composable
fun GradientText(text: String, colors: List<Color>) {
    val brush = Brush.linearGradient(
        colors,
        start = Offset(0f, 0f),
        end = Offset(0f, 350f)
    )

    Text(
        text = text,
        style = Typography.bodyMedium.copy(
            brush = brush,
        )
    )
}