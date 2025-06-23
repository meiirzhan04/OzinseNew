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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Grey400
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSee by remember { mutableStateOf(false) }
    var isClicked by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val isEmailError = isClicked && !isValidEmail(email)
    val isFormIncomplete = email.isBlank() || password.isBlank()

    val isAuth by viewModel.isAuthenticated.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(isAuth) {
        if (isAuth) {
            isLoading = false
            navController.navigate(Screen.HomeScreen.route())
        } else if (isClicked && error.isNotBlank()) {
            isLoading = false
            Toast.makeText(
                navController.context,
                error,
                Toast.LENGTH_SHORT
            ).show()
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
                .padding(horizontal = 24.dp),
        ) {
            Text(
                "Сәлем",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = Typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "Аккаунтқа кіріңіз",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = Typography.bodyLarge
            )
            Spacer(Modifier.height(30.dp))

            TextFieldBox(
                text = "Email",
                email = email,
                onValueChange = { email = it },
                image = R.drawable.ic_message,
                placeholder = "Сіздің email",
                isError = isEmailError,
                keyboardType = KeyboardType.Email,
                onClick = {}
            )
            if (isEmailError) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Қате формат", color = Color(0xFF_FF402B), style = Typography.bodySmall)
            }

            Spacer(Modifier.height(16.dp))

            TextFieldBox(
                text = "Құпия сөз",
                email = password,
                onValueChange = { password = it },
                image = R.drawable.ic_passwrod,
                placeholder = "Сіздің құпия сөзіңіз",
                isTrue = true,
                isError = false,
                isSee = isSee,
                onClick = { isSee = !isSee },
                visualTransformation = if (isSee) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )

            Spacer(Modifier.height(16.dp))

            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End),
                enabled = false
            ) {
                Text(
                    "Құпия сөзді ұмыттыңыз ба?",
                    color = Color(0xFF_B376F7),
                    style = Typography.bodySmall
                )
            }

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = {
                    isClicked = true
                    if (isFormIncomplete || isEmailError) {
                        Toast.makeText(
                            navController.context,
                            "Email немесе құпия сөз дұрыс емес",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }
                    isLoading = true
                    viewModel.login(email, password)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF_7E2DFC))
            ) {
                if (isLoading) {
                    androidx.compose.material3.CircularProgressIndicator(
                        color = White,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Кіру",
                        color = White,
                        style = Typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            TextButton(
                onClick = { navController.navigate(Screen.RegisterScreen.route()) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        "Аккаунтыныз жоқ па? ",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = Typography.bodyMedium
                    )
                    Text("Тіркелу", color = Color(0xFF_B376F7), style = Typography.bodySmall)
                }
            }

            Spacer(Modifier.height(40.dp))
            Text(
                "Немесе",
                color = Grey400,
                style = Typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))

            ButtonBox(R.drawable.ic_apple, {}, "Apple ID", MaterialTheme.colorScheme.secondary)
            Spacer(Modifier.height(16.dp))
            ButtonBox(R.drawable.ic_google, {}, "Google", MaterialTheme.colorScheme.secondary)
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
    keyboardType: KeyboardType = KeyboardType.Text,
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
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = if (isError) Color(0xFF_FF402B) else Color(0xFF_B376F7),
            unfocusedBorderColor = if (isError) Color(0xFF_FF402B) else MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        leadingIcon = {
            Image(painter = painterResource(id = image), contentDescription = null)
        },
        visualTransformation = visualTransformation,
        singleLine = true,
        trailingIcon = {
            if (isTrue) {
                IconButton(onClick = onClick) {
                    Icon(
                        painter = painterResource(id = if (!isSee) R.drawable.ic_eye_open else R.drawable.ic_eye_close),
                        contentDescription = null,
                        tint = Grey400
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