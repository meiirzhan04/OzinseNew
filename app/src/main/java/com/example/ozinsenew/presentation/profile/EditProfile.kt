package com.example.ozinsenew.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.ozinsenew.R
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White
import com.example.ozinsenew.viewmodels.ViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavHostController, viewModel: ViewModel) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var name by remember { mutableStateOf(sharedPreferences.getString("name", "") ?: "") }
    var email by remember {
        mutableStateOf(FirebaseAuth.getInstance().currentUser?.email ?: "")
    }
    var phone by remember { mutableStateOf(sharedPreferences.getString("phone", "") ?: "") }
    var birthDate by remember { mutableStateOf(sharedPreferences.getString("birthDate", "") ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Жеке деректер",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = Typography.bodyLarge,
                        color = White
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = White,
                        modifier = Modifier.clickable { navController.popBackStack() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
        ) {
            ProfileField(
                label = "Сіздің атыңыз",
                value = name,
                onValueChange = {
                    name = it
                    sharedPreferences.edit { putString("name", name) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                readOnly = false
            )
            DividerLine()
            ProfileField(
                label = "Email",
                value = email,
                onValueChange = {
                    email = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                readOnly = false
            )
            DividerLine()
            ProfileField(
                label = "Телефон",
                value = phone,
                onValueChange = {
                    phone = it
                    sharedPreferences.edit { putString("phone", phone) }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                readOnly = false
            )
            DividerLine()
            ProfileField(
                label = "Туылған күні",
                value = birthDate,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {}) {}
                }
            )
            DividerLine()
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.updateFirebaseEmail(email) { success, message ->
                        if (!success) {
                            Toast.makeText(context, "Email сәтті өзгертілді", Toast.LENGTH_SHORT)
                                .show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Қате: $message", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 24.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E2DFC))
            ) {
                androidx.compose.material.Text(
                    text = "Өзгерістерді сақтау",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Composable
fun DividerLine() {
    HorizontalDivider(
        color = Color(0xFF1C2431),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    readOnly: Boolean,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            androidx.compose.material.Text(
                text = label,
                color = Color(0xFF8E8E93),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        },
        readOnly = readOnly,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500
        ),
        trailingIcon = trailingIcon,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.White,
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified,
            textColor = Color.White,
            focusedLabelColor = Color(0xFF8E8E93),
            unfocusedLabelColor = Color(0xFF8E8E93)
        ),
        keyboardOptions = keyboardOptions,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}
