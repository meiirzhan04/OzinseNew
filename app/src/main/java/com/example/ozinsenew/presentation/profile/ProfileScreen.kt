package com.example.ozinsenew.presentation.profile

//noinspection UsingMaterialAndMaterial3Libraries
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.navigation.Screen
import com.example.ozinsenew.navigation.route
import com.example.ozinsenew.ui.theme.Background
import com.example.ozinsenew.ui.theme.BoxGray
import com.example.ozinsenew.ui.theme.Gray
import com.example.ozinsenew.ui.theme.Pink
import com.example.ozinsenew.ui.theme.TextPink
import com.example.ozinsenew.ui.theme.Typography
import com.example.ozinsenew.ui.theme.White
import com.example.ozinsenew.viewmodels.ViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: ViewModel) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("settings", Context.MODE_PRIVATE) }
    val languages = listOf("English", "Қазақша", "Русский")

    var showChangeLanguageSheet by remember { mutableStateOf(false) }
    val resetSheetState = rememberModalBottomSheetState()

    val initialIndex = remember { prefs.getInt("language_index", 1) }
    var languageSelected by rememberSaveable { mutableIntStateOf(initialIndex) }
    var selectedLanguage by remember { mutableStateOf(languages[languageSelected]) }
    var showLogOutSheet by remember { mutableStateOf(false) }

    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var name by remember { mutableStateOf(sharedPreferences.getString("name", "") ?: "") }
    val email by rememberUpdatedState(FirebaseAuth.getInstance().currentUser?.email ?: "Email жоқ")

    if (showChangeLanguageSheet) {
        ModalBottomSheet(
            onDismissRequest = { showChangeLanguageSheet = false },
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = Color(0xFF1C2431),
            sheetState = resetSheetState,
            scrimColor = Color.Black.copy(alpha = 0.5f),
            dragHandle = { CustomDragHandle() }
        ) {
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                item {
                    Column {
                        Text(
                            text = "Тіл",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(32.dp))

                        languages.forEachIndexed { index, lang ->
                            LanguageOptionRow(
                                language = lang,
                                isSelected = selectedLanguage == lang,
                                onClick = {
                                    selectedLanguage = lang
                                    languageSelected = index
                                    prefs.edit { putInt("language_index", index) }
                                    showChangeLanguageSheet = false
                                }
                            )
                            if (index != languages.lastIndex) {
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = Color(0xFF374151)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if (showLogOutSheet) {
        ModalBottomSheet(
            onDismissRequest = { showLogOutSheet = false },
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = Color(0xFF1C2431),
            sheetState = resetSheetState,
            scrimColor = Color.Black.copy(alpha = 0.5f),
            dragHandle = { CustomDragHandle() }
        ) {
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                item {
                    Column {
                        Text(
                            text = "Шығу",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Сіз шынымен аккаунтыныздан",
                            style = Typography.titleLarge,
                            color = Gray
                        )
                        Spacer(Modifier.height(32.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Pink,
                                contentColor = White
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Иә, шығу",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.padding(16.dp),
                                color = White
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BoxGray,
                            )
                        ) {
                            Text(
                                text = "Жоқ",
                                fontSize = 16.sp,
                                color = TextPink,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Профиль",
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
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_out),
                        contentDescription = "Logout",
                        modifier = Modifier.clickable {
                            viewModel.logout()
                            showLogOutSheet = true
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background),
                modifier = Modifier
                    .background(Background)
                    .padding(horizontal = 24.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
                .padding(bottom = 90.dp)
        ) {
            ProfileHeader(name, email)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(horizontal = 24.dp)
            ) {
                item {
                    TextBox("Жеке деректер", "Өңдеу", isTrue = true) {
                        navController.navigate(Screen.EditProfile.route())
                    }
                    TextBox("Құпия сөзді өзгерту", "", isTrue = true) {
                        navController.navigate(Screen.ResetPasswordScreen.route())
                    }
                    TextBox("Тіл", selectedLanguage, isTrue = true) {
                        showChangeLanguageSheet = true
                    }
                    TextBox("Ережелер мен шарттар", "", isTrue = true) {}
                    TextBox("Хабарландырулар", "", isTrue = true, isText = false) {}
                    TextBox("Қараңғы режим", "", isTrue = false, isText = false) {
                        viewModel.isDarkTheme = !viewModel.isDarkTheme
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String, email: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(BoxGray)
            .padding(bottom = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_avatar),
                contentDescription = "Avatar",
                modifier = Modifier.size(140.dp)
            )
            Text(
                text = if (name.isEmpty()) "Менің профилім" else name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
            Text(
                text = email,
                style = Typography.bodyMedium,
                color = Gray
            )
        }
    }
}

@Composable
fun TextBox(
    text: String,
    title: String,
    isTrue: Boolean,
    isText: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = Typography.bodyLarge,
            color = White
        )

        if (isText) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (title.isNotEmpty()) {
                    Text(text = title, style = Typography.labelSmall, color = Gray)
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Pink
                )
            }
        } else {
            ToggleSwitch(onClick = onClick)
        }
    }

    if (isTrue) {
        HorizontalDivider(color = BoxGray)
    }
}

@Composable
fun ToggleSwitch(onClick: () -> Unit = {}) {
    var checkedState by remember { mutableStateOf(false) }
    val activeColor = Color(0xFFB376F7)

    Switch(
        checked = checkedState,
        onCheckedChange = {
            checkedState = it
            onClick()
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = activeColor,
        )
    )
}

@Composable
fun CustomDragHandle() {
    Box(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .size(width = 65.dp, height = 5.dp)
            .background(Color.Gray, shape = RoundedCornerShape(2.dp))
    )
}

@Composable
fun LanguageOptionRow(
    language: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.W600,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Selected"
            )
        } else {
            Box(modifier = Modifier.size(24.dp))
        }
    }
}
