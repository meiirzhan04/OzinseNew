package com.example.ozinsenew.presentation.home

import android.R.attr.onClick
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.White
import com.example.ozinsenew.viewmodels.ViewModel

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DetailScreen(navController: NavController, itemId: Int, viewModel: ViewModel) {

    val box = viewModel.getBoxById(itemId)
    box?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(it.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 48.dp)
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
                ElementBox(R.drawable.ic_bookmark, "Тізімге қосу")
                ElementBox(R.drawable.ic_play, "")
                ElementBox(R.drawable.ic_share, "Бөлісу")
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 320.dp)
                    .background(
                        Background,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    ),
            ) {
                item {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = it.title,
                            color = White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "2020 • Телехикая • 5 сезон, 46 серия, 7 мин.",
                            color = Gray,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = it.description,
                            color = White,
                            fontSize = 14.sp
                        )
                        TextButton(
                            onClick = {}
                        ) {
                            Text(
                                text = "Толығырақ",
                                color = MaterialTheme.colors.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Режиссер: Бақдәулет Әлімбеков", color = White)
                        Text(text = "Продюсер: Сандуғаш Кенжебаева", color = White)
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = "Бөлімдер", color = White, fontWeight = FontWeight.Bold)
                        Text(text = "5 сезон, 46 серия", color = White)
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
            contentDescription = ""
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Gray,
            fontWeight = FontWeight.W600
        )
    }
}