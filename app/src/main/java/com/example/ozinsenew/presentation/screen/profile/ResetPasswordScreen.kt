package com.example.ozinsenew.presentation.screen.profile

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ozinsenew.R
import com.example.ozinsenew.R.drawable.ic_eye_close
import com.example.ozinsenew.R.drawable.ic_eye_open
import com.example.ozinsenew.R.drawable.ic_passwrod
import com.example.ozinsenew.presentation.ui.theme.Grey400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    var passwordFirst by remember { mutableStateOf("") }
    var passwordSecond by remember { mutableStateOf("") }
    var isPasswordVisibleFirst by remember { mutableStateOf(false) }
    var isPasswordVisibleSecond by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 24.dp),
                title = {
                    Text(
                        text = "Құпия сөзді өзгерту",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    navController.popBackStack()
                                },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        }
    ) {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.primaryContainer,
                thickness = 1.dp
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Құпия сөз",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = passwordFirst,
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                    ),
                    singleLine = true,
                    onValueChange = {
                        passwordFirst = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    placeholder = {
                        Text(
                            text = "Сіздің құпия сөзіңіз",
                            color = Grey400,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        focusedBorderColor = Color(0xFF_B376F7),
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = ic_passwrod),
                            contentDescription = null,
                            tint = Color(0xFF_9CA3AF),
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = if (isPasswordVisibleFirst) ic_eye_open else ic_eye_close),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        isPasswordVisibleFirst = !isPasswordVisibleFirst
                                    },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            colorFilter = ColorFilter.tint(Grey400)
                        )
                    },
                    visualTransformation = if (isPasswordVisibleFirst) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Құпия сөзді қайталаңыз",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = passwordSecond,
                    singleLine = true,
                    onValueChange = {
                        passwordSecond = it
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.background,
                        focusedBorderColor = Color(0xFF_B376F7),
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    placeholder = {
                        Text(
                            text = "Сіздің құпия сөзіңіз",
                            color = Color(0xFF_9CA3AF),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,

                            )
                    },
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = ic_passwrod),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp),
                        )
                    },
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = if (isPasswordVisibleSecond) ic_eye_open else ic_eye_close),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        isPasswordVisibleSecond = !isPasswordVisibleSecond
                                    },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            colorFilter = ColorFilter.tint(Gray)
                        )
                    },
                    visualTransformation = if (isPasswordVisibleSecond) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        if (passwordFirst.isEmpty() || passwordSecond.isEmpty()) {
                            Toast.makeText(
                                navController.context,
                                "Empty or is not equal",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF_7E2DFC)
                    )
                ) {
                    Text(
                        text = "Өзгерістерді сақтау",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 16.dp,
                                horizontal = 16.dp
                            ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}