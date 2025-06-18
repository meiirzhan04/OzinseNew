package com.example.ozinsenew.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Іздеу",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = Typography.bodyLarge,
                        color = White
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = White,
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background),
                modifier = Modifier
                    .background(Background)
                    .padding(horizontal = 24.dp)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues = paddingValues)
        ) {
            item {
                HorizontalDivider(
                    color = Color(0xFF_1C2431),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.8f)
                    ) {
                        OutlinedTextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFF_9753F0),
                                unfocusedBorderColor = Color(0xFF_1C2431),
                                textColor = Color.White,
                                cursorColor = Color(0xFF_9753F0),
                                backgroundColor = Color(0xFF_1C2431),
                            ),
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                textAlign = TextAlign.Start,
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "Іздеу",
                                    color = Color(0xFF_9CA3AF),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W600,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            },
                            trailingIcon = {
                                if (search.isNotEmpty()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_exit),
                                        contentDescription = null,
                                        modifier = Modifier.clickable(
                                            onClick = {}
                                        )
                                    )
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_searchicon),
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Іздеу нәтижелері",
                    color = Color.White,
                    style = Typography.headlineSmall,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}