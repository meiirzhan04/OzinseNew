package com.example.ozinsenew.presentation.screen.login


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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.presentation.navigation.Screen
import com.example.ozinsenew.presentation.navigation.route
import com.example.ozinsenew.presentation.ui.theme.Typography
import com.example.ozinsenew.presentation.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsState()

    val isEmailError = isClicked && !isValidEmail(email)
    val isPasswordMismatch = isClicked && password != confirmPassword
    val isFormIncomplete = email.isBlank() || password.isBlank() || confirmPassword.isBlank()

    LaunchedEffect(state.isUserLoggedIn) {
        if (state.isUserLoggedIn) {
            viewModel.resetJustRegistered()
            navController.navigate(Screen.LoginScreen.route())
        }
    }
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.takeIf { it.isNotBlank() }?.let {
            Toast.makeText(navController.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            Spacer(Modifier.height(16.dp))
            Text(
                "Деректерді толтырыңыз",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = Typography.bodyLarge
            )
            Spacer(Modifier.height(30.dp))

            EmailField(email, isEmailError) { email = it }
            if (isEmailError) Text(
                "Қате email форматы",
                color = Color.Red,
                style = Typography.bodySmall
            )

            Spacer(Modifier.height(16.dp))
            PasswordField(
                value = password,
                label = "Құпия сөз",
                visible = isPasswordVisible,
                onVisibleChange = { isPasswordVisible = it },
                onValueChange = { password = it }
            )

            Spacer(Modifier.height(16.dp))
            PasswordField(
                value = confirmPassword,
                label = "Құпия сөзді қайталаңыз",
                visible = isConfirmPasswordVisible,
                onVisibleChange = { isConfirmPasswordVisible = it },
                onValueChange = { confirmPassword = it },
                isError = isPasswordMismatch
            )
            if (isPasswordMismatch) Text(
                "Құпия сөздер сәйкес емес",
                color = Color.Red,
                style = Typography.bodySmall
            )

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = {
                    isClicked = true
                    when {
                        isFormIncomplete -> showToast(navController, "Барлық өрістерді толтырыңыз")
                        isEmailError -> showToast(navController, "Email дұрыс емес")
                        isPasswordMismatch -> showToast(navController, "Құпия сөздер сәйкес емес")
                        else -> viewModel.register(email, password)
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2DFC))
            ) {
                if (state.isLoading) {
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

            Spacer(Modifier.height(24.dp))
            TextButton(
                onClick = { navController.navigate(Screen.LoginScreen.route()) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        "Сізде аккаунт бар ма? ",
                        style = Typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text("Кіру", color = Color(0xFFB376F7), style = Typography.bodySmall)
                }
            }
        }
    }
}

@Composable
private fun EmailField(email: String, isError: Boolean, onValueChange: (String) -> Unit) {
    TextFieldBox(
        text = "Email",
        email = email,
        onValueChange = onValueChange,
        image = R.drawable.ic_message,
        placeholder = "Сіздің email",
        isTrue = false,
        isError = isError,
        onClick = {},
        keyboardType = KeyboardType.Email
    )
}

@Composable
private fun PasswordField(
    value: String,
    label: String,
    visible: Boolean,
    onVisibleChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    TextFieldBox(
        text = label,
        email = value,
        onValueChange = onValueChange,
        image = R.drawable.ic_passwrod,
        placeholder = "Сіздің құпия сөзіңіз",
        isTrue = true,
        isSee = visible,
        isError = isError,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        onClick = { onVisibleChange(!visible) },
        keyboardType = KeyboardType.Password
    )
}

private fun showToast(navController: NavController, message: String) {
    Toast.makeText(navController.context, message, Toast.LENGTH_SHORT).show()
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    return email.matches(emailRegex.toRegex())
}