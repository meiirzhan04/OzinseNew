package com.example.ozinsenew.presentation.screen.profile


//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.ripple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ozinsenew.R
import com.example.ozinsenew.data.theme.ThemeViewModel
import com.example.ozinsenew.presentation.navigation.Screen
import com.example.ozinsenew.presentation.navigation.route
import com.example.ozinsenew.presentation.ui.theme.Grey200
import com.example.ozinsenew.presentation.ui.theme.Grey300
import com.example.ozinsenew.presentation.ui.theme.Grey400
import com.example.ozinsenew.presentation.ui.theme.Red300
import com.example.ozinsenew.presentation.ui.theme.Red400
import com.example.ozinsenew.presentation.ui.theme.Typography
import com.example.ozinsenew.presentation.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("settings", Context.MODE_PRIVATE) }
    val languages = listOf("English", "Қазақша", "Русский")

    var showChangeLanguageSheet by remember { mutableStateOf(false) }

    val initialIndex = remember { prefs.getInt("language_index", 1) }
    var languageSelected by rememberSaveable { mutableIntStateOf(initialIndex) }
    var selectedLanguage by remember { mutableStateOf(languages[languageSelected]) }

    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    var name by remember { mutableStateOf(sharedPreferences.getString("name", "") ?: "") }
    val email by rememberUpdatedState(FirebaseAuth.getInstance().currentUser?.email ?: "Email жоқ")

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }


    if (showChangeLanguageSheet) {
        ModalBottomSheet(
            onDismissRequest = { showChangeLanguageSheet = false },
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
            sheetState = sheetState,
            scrimColor = Color.Black.copy(alpha = 0.5f),
            dragHandle = { CustomDragHandle() }
        ) {
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                item {
                    Column {
                        Text(
                            text = "Тіл",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                                },
                            )
                            if (index != languages.lastIndex) {
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.primaryContainer
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    /*if (showLogOutSheet) {
        ModalBottomSheet(
            onDismissRequest = { showLogOutSheet = false },
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            containerColor = Color(0xFF1C2431),
            sheetState = sheetState,
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
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                showLogOutSheet = false
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (isClickedYes) Pink else BoxGray.copy(),
                            ),
                        ) {
                            Text(
                                text = "Иә, шығу",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.padding(16.dp),
                                color = if (isClickedYes) White else TextPink
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                isClickedYes = !isClickedYes
                                isClickedNo = true
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (isClickedNo) Pink else BoxGray,

                                )
                        ) {
                            Text(
                                text = "Жоқ",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W600,
                                modifier = Modifier.padding(16.dp),
                                color = if (isClickedNo) White else TextPink
                            )
                        }
                    }
                }
            }
        }
    }*/
    if (showSheet) {
        LogoutBottomSheet(
            onLogout = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    showSheet = false
                    FirebaseAuth.getInstance().signOut()
                }
                viewModel.logout()
                navController.navigate(Screen.LoginScreen.route())
            },
            onCancel = {
                scope.launch {
                    sheetState.hide()
                    showSheet = false
                }
            },
            sheetState = sheetState,
            onDismiss = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    showSheet = false
                }
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = Color(0xFF_FF402B),
                ),
                title = {
                    Text(
                        text = "Профиль",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = Typography.bodyLarge,
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .clickable(
                                onClick = { navController.popBackStack() },
                                indication = ripple(bounded = false),
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_out),
                        contentDescription = "Logout",
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .clickable(
                                onClick = { showSheet = true },
                                indication = ripple(bounded = false),
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(bottom = 70.dp)
        ) {
            ProfileHeader(name, email)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 24.dp)
            ) {
                item {
                    TextBox("Жеке деректер", "Өңдеу", isTrue = true, isTheme = false) {
                        navController.navigate(Screen.EditProfile.route())
                    }
                    TextBox("Құпия сөзді өзгерту", "", isTrue = true, isTheme = false) {
                        navController.navigate(Screen.ResetPasswordScreen.route())
                    }
                    TextBox("Тіл", selectedLanguage, isTrue = true, isTheme = false) {
                        showChangeLanguageSheet = true
                    }
                    TextBox("Ережелер мен шарттар", "", isTrue = true, isTheme = false) {}
                    TextBox(
                        "Хабарландырулар",
                        "",
                        isTrue = true,
                        isText = false,
                        isTheme = false
                    ) {}
                    TextBox("Қараңғы режим", "", isTrue = false, isText = false, isTheme = true) {}
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
            .background(MaterialTheme.colorScheme.onTertiaryContainer)
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
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                letterSpacing = 0.5.sp
            )
            Text(
                text = email,
                style = Typography.bodyMedium,
                color = Grey400,
                letterSpacing = 0.5.sp
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
    isTheme: Boolean,
    onClick: () -> Unit,
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
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        if (isText) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (title.isNotEmpty()) {
                    Text(text = title, style = Typography.labelSmall, color = Grey400)
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Red300
                )
            }
        } else {
            if (isTheme) {
                ThemeToggleSwitch()
            } else {
                ToggleSwitch(onClick = onClick)
            }
        }
    }

    if (isTrue) {
        HorizontalDivider(color = MaterialTheme.colorScheme.primaryContainer, thickness = 1.dp)
    }
}

@Composable
fun ToggleSwitch(onClick: () -> Unit = {}) {
    var checkedState by remember { mutableStateOf(false) }
    val activeColor = Red400

    Switch(
        checked = checkedState,
        onCheckedChange = {
            checkedState = it
            onClick()
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Grey200,
            checkedTrackColor = activeColor,
            uncheckedThumbColor = White,
            uncheckedTrackColor = Grey200,
            uncheckedBorderColor = Grey200
        )
    )
}

@Composable
fun CustomDragHandle() {
    Box(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .size(width = 60.dp, height = 5.dp)
            .background(Grey300, shape = RoundedCornerShape(2.dp))
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
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.W600,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(shape = CircleShape)
                    .background(Red400)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.Center)
                )
            }
        } else {
            Box(modifier = Modifier.size(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutBottomSheet(
    onLogout: () -> Unit,
    onCancel: () -> Unit,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    var yesPressed by remember { mutableStateOf(false) }
    var noPressed by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        scrimColor = Color.Black.copy(alpha = 0.5f),
        dragHandle = { CustomDragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
        ) {
            Text(
                text = "Шығу",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Сіз шынымен аккаунтыңыздан",
                color = Grey400,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    yesPressed = true
                    onLogout()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (yesPressed) Color(0xFF7B36CC) else Color(0xFF9747FF)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Иә, шығу",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Жоқ",
                color = if (noPressed) Color(0xFFB86BFF) else Color(0xFF9747FF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        noPressed = true
                        onCancel()
                    },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ThemeToggleSwitch(viewModel: ThemeViewModel = viewModel()) {
    val isDark = viewModel.isDarkMode.collectAsState().value
    val activeColor = Red400
    Switch(
        checked = isDark,
        onCheckedChange = { viewModel.toggleTheme() },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Grey200,
            checkedTrackColor = activeColor,
            uncheckedThumbColor = White,
            uncheckedTrackColor = Grey200,
            uncheckedBorderColor = Grey200
        )
    )
}
