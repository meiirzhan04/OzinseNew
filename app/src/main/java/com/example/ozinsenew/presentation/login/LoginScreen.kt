package com.example.ozinsenew.presentation.login

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Grey400
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.viewmodels.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: ViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSee by remember { mutableStateOf(false) }
    val isClicked = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = {},
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
                text = "Сәлем",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Аккаунтқа кіріңіз",
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
                isError = isClicked.value && !isValidEmail(email) && email.isNotEmpty() && password.isNotEmpty(),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (isClicked.value && !isValidEmail(email) && email.isNotEmpty() && password.isNotEmpty()) {
                Text(
                    text = "Қате формат",
                    color = Color(0xFF_FF402B),
                    style = Typography.bodySmall,
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            TextFieldBox(
                text = "Құпия сөз",
                email = password,
                onValueChange = { newPassword ->
                    password = newPassword
                },
                image = R.drawable.ic_passwrod,
                placeholder = "Сіздің құпия сөзіңіз",
                isTrue = true,
                visualTransformation = if (isSee) VisualTransformation.None else PasswordVisualTransformation(),
                onClick = {
                    isSee = !isSee
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End),
                enabled = false
            ) {
                Text(
                    text = "Құпия сөзді ұмыттыңыз ба?",
                    color = Color(0xFF_B376F7),
                    style = Typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    isClicked.value = true
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(navController.context, "Not Found", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    } else {
                        viewModel.login(email, password)
                        if (viewModel.isAuthenticated) {
                            navController.navigate(Screen.HomeScreen.route())
                        } else {
                            Toast.makeText(
                                navController.context,
                                viewModel.errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF_7E2DFC)
                )
            ) {
                Text(
                    text = "Кіру",
                    color = White,
                    style = Typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            TextButton(
                onClick = {
                    navController.navigate(Screen.RegisterScreen.route())
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        text = "Аккаунтыныз жоқ па? ",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = Typography.bodyMedium,
                    )
                    Text(
                        text = "Тіркелу",
                        color = Color(0xFF_B376F7),
                        style = Typography.bodySmall,
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Немесе",
                color = Grey400,
                style = Typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonBox(
                image = R.drawable.ic_apple,
                onClick = {},
                text = "Apple ID",
                containerColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonBox(
                image = R.drawable.ic_google,
                onClick = {},
                text = "Google",
                containerColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun TextFieldBox(
    text: String,
    email: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    image: Int,
    isTrue: Boolean = false,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isSee: Boolean = false,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = Typography.bodySmall,
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = email,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = Typography.bodyLarge,
                color = MaterialTheme.colorScheme.surface
            )
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        textStyle = Typography.bodyLarge,
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = if (isError) Color(0xFF_FF402B) else Color(0xFF_B376F7),
            unfocusedBorderColor = if (isError) Color(0xFF_FF402B) else MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Email",
            )
        },
        visualTransformation = visualTransformation,
        singleLine = true,
        trailingIcon = {
            if (isTrue) {
                IconButton(
                    onClick = onClick,
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (!isSee) R.drawable.ic_eye_open else R.drawable.ic_eye_close
                        ),
                        contentDescription = "Email",
                        tint = Grey400,
                    )
                }
            }
        }
    )
}


@Composable
fun ButtonBox(
    image: Int,
    onClick: () -> Unit,
    text: String,
    containerColor: Color
) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        )
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Google"
        )
        Text(
            text = "$text-мен тіркеліңіз",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = Typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}