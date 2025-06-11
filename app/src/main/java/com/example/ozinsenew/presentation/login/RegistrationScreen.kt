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
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.TextPink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White
import com.example.ozinsenew.viewmodels.ViewModel

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
                            tint = White,
                        )
                    }
                },
                backgroundColor = Background,
                elevation = 0.dp,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
                .padding(24.dp),
        ) {
            Text(
                text = "Тіркелу",
                color = White,
                style = Typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Деректерді толтырыңыз",
                color = Gray,
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
                            navController.navigate(Screen.LoginScreen)
                        }
                    } else {
                        Toast.makeText(navController.context, "Empty", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Pink
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
                onClick = {},
                enabled = true,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        text = "Сізде аккаунт бар ма? ",
                        color = White,
                        style = Typography.bodyMedium,
                    )
                    Text(
                        text = "Кіру",
                        color = TextPink,
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