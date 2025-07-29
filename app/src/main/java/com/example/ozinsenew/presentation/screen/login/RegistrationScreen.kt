package com.example.ozinsenew.presentation.screen.login


//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.presentation.navigation.Screen
import com.example.ozinsenew.presentation.navigation.route
import com.example.ozinsenew.presentation.ui.theme.Typography
import com.example.ozinsenew.presentation.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val isEmailError = isClicked && !isValidEmail(email)
    val isPasswordMismatch = isClicked && password != confirmPassword
    val isFormIncomplete = email.isBlank() || password.isBlank() || confirmPassword.isBlank()

    val justRegistered by viewModel.justRegistered.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(justRegistered) {
        if (justRegistered) {
            navController.navigate(Screen.LoginScreen.route())
        }
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotBlank()) {
            Toast.makeText(navController.context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.padding(start = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                },
                backgroundColor = MaterialTheme.colorScheme.background,
                elevation = 0.dp,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                "Тіркелу",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Деректерді толтырыңыз",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(30.dp))

            TextFieldBox(
                text = "Email",
                email = email,
                onValueChange = { email = it },
                image = R.drawable.ic_message,
                placeholder = "Сіздің email",
                isTrue = false,
                isError = isEmailError,
                onClick = {},
                keyboardType = KeyboardType.Email
            )
            if (isEmailError) {

                Spacer(modifier = Modifier.height(16.dp))
                Text("Қате email форматы", color = Color.Red, style = Typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldBox(
                text = "Құпия сөз",
                email = password,
                onValueChange = { password = it },
                image = R.drawable.ic_passwrod,
                placeholder = "Сіздің құпия сөзіңіз",
                isTrue = true,
                isSee = isPasswordVisible,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onClick = { isPasswordVisible = !isPasswordVisible },
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldBox(
                text = "Құпия сөзді қайталаңыз",
                email = confirmPassword,
                onValueChange = { confirmPassword = it },
                image = R.drawable.ic_passwrod,
                placeholder = "Сіздің құпия сөзіңіз",
                isTrue = true,
                isSee = isConfirmPasswordVisible,
                isError = isPasswordMismatch,
                visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible },
                keyboardType = KeyboardType.Password
            )
            if (isPasswordMismatch) {
                Text("Құпия сөздер сәйкес емес", color = Color.Red, style = Typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    isClicked = true
                    when {
                        isFormIncomplete -> {
                            Toast.makeText(
                                navController.context,
                                "Барлық өрістерді толтырыңыз",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        isEmailError -> {
                            Toast.makeText(
                                navController.context,
                                "Email дұрыс емес",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        isPasswordMismatch -> {
                            Toast.makeText(
                                navController.context,
                                "Құпия сөздер сәйкес емес",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            viewModel.register(
                                email,
                                password
                            )
                            viewModel.resetJustRegistered()
                            isLoading = true
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF_7E2DFC))
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = White,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Тіркелу",
                        color = White,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {
                    navController.navigate(Screen.LoginScreen.route())
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        text = "Сізде аккаунт бар ма? ",
                        style = Typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Кіру",
                        color = Color(0xFF_B376F7),
                        style = Typography.bodySmall
                    )
                }
            }
        }
    }
}

@Composable
fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    return email.matches(emailRegex.toRegex())
}