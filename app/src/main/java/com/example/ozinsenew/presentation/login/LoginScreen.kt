package com.example.ozinsenew.presentation.login

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ozinsenew.R
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.BorderGray
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.DarkGray
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.TextPink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
                text = "Сәлем",
                color = White,
                style = Typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Аккаунтқа кіріңіз",
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
                isTrue = false
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
                isTrue = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End),
                enabled = false
            ) {
                Text(
                    text = "Құпия сөзді ұмыттыңыз ба?",
                    color = TextPink,
                    style = Typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Pink
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
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row {
                    Text(
                        text = "Аккаунтыныз жоқ па? ",
                        color = White,
                        style = Typography.bodyMedium,
                    )
                    Text(
                        text = "Тіркелу",
                        color = TextPink,
                        style = Typography.bodySmall,
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Немесе",
                color = Gray,
                style = Typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonBox(
                image = R.drawable.ic_apple,
                onClick = {},
                text = "Apple ID"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonBox(
                image = R.drawable.ic_google,
                onClick = {},
                text = "Google"
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
    isTrue: Boolean = false
) {
    Text(
        text = text,
        style = Typography.bodySmall,
        color = White
    )
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = email,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = Typography.bodyLarge,
                color = Gray
            )
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        textStyle = Typography.bodyMedium,
        colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = BoxGray,
            focusedBorderColor = Pink,
            unfocusedBorderColor = BorderGray,
            textColor = White,
        ),
        leadingIcon = {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Email",
            )
        },
        singleLine = true,
        trailingIcon = {
            if (isTrue) {
                IconButton(
                    onClick = { },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye_open),
                        contentDescription = "Email",
                        tint = Gray,
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
    text: String
) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGray
        )
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Google"
        )
        Text(
            text = "$text-мен тіркеліңіз",
            color = White,
            style = Typography.bodySmall,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}