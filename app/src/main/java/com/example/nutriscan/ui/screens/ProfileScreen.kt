package com.example.nutriscan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nutriscan.ui.components.BottomNavigationBar
import com.example.nutriscan.ui.components.profile.AppVersionCard
import com.example.nutriscan.ui.components.profile.BmiCard
import com.example.nutriscan.ui.components.profile.ProfileActionButtons
import com.example.nutriscan.ui.components.profile.ProfileHeader
import com.example.nutriscan.ui.components.profile.UserInfoCard
import com.example.nutriscan.ui.theme.getNutriAppColors
import com.example.nutriscan.ui.viewmodel.NutriViewModel
import com.example.nutriscan.utils.openPdfFromAssets

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: NutriViewModel
) {
    val context = LocalContext.current

    val currentUser by viewModel.currentUser.collectAsState()
    val bmiResult by viewModel.bmiResult.collectAsState()
    val bmiStatus by viewModel.bmiStatus.collectAsState()
    val goalCalories by viewModel.goalCalories.collectAsState()
    val goalProtein by viewModel.goalProtein.collectAsState()
    val goalCarbs by viewModel.goalCarbs.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    val appColors = getNutriAppColors(isDarkMode)

    val gradientPrimary = Brush.horizontalGradient(
        colors = listOf(
            appColors.primary.copy(alpha = 0.75f),
            appColors.primary
        )
    )

    val isProfileSetup = currentUser != null &&
            (currentUser?.weight ?: 0.0) > 0.0 &&
            (currentUser?.height ?: 0.0) > 0.0

    var showConfirmClear by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "profile_screen"
            )
        },
        containerColor = appColors.background
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                ProfileHeader(
                    currentUser = currentUser,
                    gradientPrimary = gradientPrimary,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary
                )
            }

            item {
                UserInfoCard(
                    currentUser = currentUser,
                    cardBackground = appColors.card,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary,
                    dividerColor = appColors.border
                )
            }

            item {
                BmiCard(
                    bmiResult = bmiResult,
                    bmiStatus = bmiStatus,
                    goalCalories = goalCalories,
                    goalProtein = goalProtein,
                    goalCarbs = goalCarbs,
                    primaryColor = appColors.primary,
                    cardBackground = appColors.card,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary,
                    orangeColor = appColors.orange
                )
            }

            item {
                ProfileActionButtons(
                    primaryColor = appColors.primary,
                    warningColor = appColors.warning,
                    showClearProfile = isProfileSetup,
                    isDarkMode = isDarkMode,
                    cardBackground = appColors.card,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary,
                    borderColor = appColors.border,
                    onToggleTheme = {
                        viewModel.toggleDarkMode()
                    },
                    onEditProfile = {
                        navController.navigate("setup_screen")
                    },
                    onOpenGuidePdf = {
                        openPdfFromAssets(
                            context = context,
                            fileName = "huong_dan_su_dung_NutriSxcan.pdf"
                        )
                    },
                    onClearProfile = {
                        showConfirmClear = true
                    }
                )
            }

            item {
                AppVersionCard(
                    cardBackground = appColors.card,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary,
                    primaryColor = appColors.primary
                )
            }

            item {
                Spacer(modifier = Modifier.height(88.dp))
            }
        }
    }

    if (showConfirmClear) {
        AlertDialog(
            onDismissRequest = {
                showConfirmClear = false
            },
            containerColor = appColors.card,
            titleContentColor = appColors.textPrimary,
            textContentColor = appColors.textPrimary,
            title = {
                Text(
                    text = "Xóa thông tin?",
                    color = appColors.textPrimary
                )
            },
            text = {
                Text(
                    text = "Bạn có chắc muốn xóa thông tin cá nhân không? Sau khi xóa, ứng dụng sẽ quay về trạng thái chưa thiết lập và dùng mức khuyến nghị mặc định 2000 Calo.",
                    color = appColors.textSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.clearUserProfile()
                        showConfirmClear = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = appColors.primary
                    )
                ) {
                    Text(text = "Xóa")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showConfirmClear = false
                    }
                ) {
                    Text(
                        text = "Hủy",
                        color = appColors.primary
                    )
                }
            }
        )
    }
}