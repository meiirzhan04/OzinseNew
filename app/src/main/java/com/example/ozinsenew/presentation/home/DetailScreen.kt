package com.example.ozinsenew.presentation.home

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.ui.theme.Grey300
import com.example.ozinsenew.ui.theme.Grey400
import com.example.ozinsenew.ui.theme.Grey600
import com.example.ozinsenew.ui.theme.Red300
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.viewmodels.ListViewModel
import com.example.ozinsenew.viewmodels.ViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DetailScreen(
    navController: NavController,
    itemId: Int,
    viewModel: ViewModel,
    listViewModel: ListViewModel,
    paddingValues: PaddingValues
) {
    val scope = rememberCoroutineScope()
    val isBookmarked = remember { mutableStateOf(false) }


    val gradientColors = listOf(
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0f),
    )
    val box = viewModel.getBoxById(itemId)
    box?.let {
        LaunchedEffect(Unit) {
            val currentItem = ListItems(
                name = box.title,
                image = box.image,
                data = box.description,
                category = box.category
            )
            isBookmarked.value = listViewModel.isBookmarked(currentItem)
        }
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(it.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentScale = ContentScale.Crop,
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 36.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = White,
                    modifier = Modifier.clickable(
                        onClick = {
                            navController.popBackStack()
                        },
                        indication = ripple(bounded = false),
                        interactionSource = MutableInteractionSource()
                    )
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElementBox(
                    if (isBookmarked.value) R.drawable.ic_bookmark_pink else R.drawable.ic_bookmark,
                    "Тізімге қосу",
                    onClick = {
                        scope.launch {

                            val item = ListItems(
                                name = box.title,
                                image = box.image,
                                data = box.description,
                                category = box.category
                            )
                            if (isBookmarked.value) {
                                listViewModel.delete(item)
                            } else {
                                listViewModel.insert(item)
                            }
                            isBookmarked.value = !isBookmarked.value
                        }
                    },
                )
                ElementBox(R.drawable.ic_play, "")
                ElementBox(R.drawable.ic_share, "Бөлісу")
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 320.dp)
                    .padding(paddingValues)
                    .background(
                        MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    ),
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
                                onClick = {},
                                indication = ripple(bounded = true),
                                interactionSource = MutableInteractionSource()
                            )
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row {
                            Text(
                                text = "Режиссер: ",
                                color = Grey600,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                            Text(
                                text = "  Бақдәулет Әлімбеков",
                                color = Grey400,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                        Spacer(Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "Продюсер: ",
                                color = Grey600,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                            Text(
                                text = " Сандуғаш Кенжебаева",
                                color = Grey400,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
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
                                    contentDescription = "Back",
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