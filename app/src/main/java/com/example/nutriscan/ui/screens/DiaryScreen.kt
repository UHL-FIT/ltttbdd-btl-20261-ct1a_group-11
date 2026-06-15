package com.example.nutriscan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nutriscan.data.local.entity.FoodDiaryEntity
import com.example.nutriscan.ui.components.BottomNavigationBar
import com.example.nutriscan.ui.components.common.DateSelector
import com.example.nutriscan.ui.components.diary.DiaryFoodItem
import com.example.nutriscan.ui.components.diary.DiarySummaryCard
import com.example.nutriscan.ui.components.diary.EditFoodDialog
import com.example.nutriscan.ui.theme.getNutriAppColors
import com.example.nutriscan.ui.viewmodel.NutriViewModel

@Composable
fun DiaryScreen(
    navController: NavHostController,
    viewModel: NutriViewModel
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val foodList by viewModel.todayFoodList.collectAsState()

    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalProtein by viewModel.totalProtein.collectAsState()
    val totalCarbs by viewModel.totalCarbs.collectAsState()

    val isDarkMode by viewModel.isDarkMode.collectAsState()
    val appColors = getNutriAppColors(isDarkMode)

    val backgroundColor = appColors.background
    val primaryColor = appColors.primary
    val warningColor = appColors.warning

    var selectedFood by remember {
        mutableStateOf<FoodDiaryEntity?>(null)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "diary_screen"
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Column {
                    Text(
                        text = "Nhật ký ăn uống",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = appColors.textPrimary
                        )
                    )

                    Text(
                        text = "Theo dõi, sửa gram và xóa món ăn theo từng ngày",
                        color = appColors.textSecondary
                    )
                }
            }

            item {
                DateSelector(
                    title = "Ngày xem nhật ký",
                    selectedDate = selectedDate,
                    primaryColor = primaryColor,
                    cardBackground = appColors.card,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary,
                    onPreviousDay = {
                        viewModel.goToPreviousDay()
                    },
                    onNextDay = {
                        viewModel.goToNextDay()
                    },
                    onDateSelected = { date ->
                        viewModel.changeSelectedDate(date)
                    }
                )
            }

            item {
                DiarySummaryCard(
                    totalCalories = totalCalories,
                    totalProtein = totalProtein,
                    totalCarbs = totalCarbs,
                    primaryColor = primaryColor,
                    cardBackground = appColors.card,
                    textPrimary = appColors.textPrimary,
                    textSecondary = appColors.textSecondary,
                    orangeColor = appColors.orange,
                    blueColor = appColors.blue
                )
            }

            item {
                Text(
                    text = "Danh sách món ăn",
                    fontWeight = FontWeight.Bold,
                    color = appColors.textPrimary
                )
            }

            if (foodList.isEmpty()) {
                item {
                    Text(
                        text = "Ngày này chưa có món ăn nào. Về trang chủ để thêm món.",
                        color = appColors.textSecondary,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                items(foodList) { food ->
                    DiaryFoodItem(
                        food = food,
                        warningColor = warningColor,
                        cardBackground = appColors.card,
                        textPrimary = appColors.textPrimary,
                        textSecondary = appColors.textSecondary,
                        onItemClick = { selected ->
                            selectedFood = selected
                        },
                        onDeleteClick = { selected ->
                            viewModel.deleteFoodFromDiary(selected)
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(88.dp))
            }
        }
    }

    selectedFood?.let { food ->
        EditFoodDialog(
            food = food,
            primaryColor = primaryColor,
            warningColor = warningColor,
            cardBackground = appColors.card,
            textPrimary = appColors.textPrimary,
            textSecondary = appColors.textSecondary,
            onDismiss = {
                selectedFood = null
            },
            onSave = { selected, newGram ->
                viewModel.updateFoodGram(
                    food = selected,
                    newGram = newGram
                )

                selectedFood = null
            }
        )
    }
}