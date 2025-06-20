package com.example.ozinsenew.presentation.login


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
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.viewmodels.ViewModel
import com.google.firebase.annotations.concurrent.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: ViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
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
                .padding(horizontal = 24.dp),
        ) {
            Text(
                text = "Тіркелу",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Деректерді толтырыңыз",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = Typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextFieldBox(
                text = "Email",
                email = email,
                onValueChange = { newEmail ->
                    email = newEmail
                },
                image = R.drawable.ic_message,
                placeholder = "Сіздің email",
                isTrue = false,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldBox(
                text = "Құпия сөз",
                email = password,
                onValueChange = { newPassword ->
                    password = newPassword
                },
                image = R.drawable.ic_passwrod,
                placeholder = "Сіздің құпия сөзіңіз",
                isTrue = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldBox(
                text = "Құпия сөзді қайталаңыз",
                email = confirmPassword,
                onValueChange = { newPassword ->
                    confirmPassword = newPassword
                },
                image = R.drawable.ic_passwrod,
                placeholder = "Сіздің құпия сөзіңіз",
                isTrue = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                        if (password != confirmPassword) {
                            Toast.makeText(
                                navController.context,
                                "Password not match",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.register(email, password)
                            navController.navigate(Screen.LoginScreen.route())
                        }
                    } else {
                        Toast.makeText(navController.context, "Empty", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF_7E2DFC)
                )
            ) {
                Text(
                    text = "Тіркелу",
                    color = White,
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    navController.navigate(Screen.LoginScreen.route())
                },
                enabled = true,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        text = "Сізде аккаунт бар ма? ",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = Typography.bodyMedium,
                    )
                    Text(
                        text = "Кіру",
                        color = Color(0xFF_B376F7),
                        style = Typography.bodySmall,
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