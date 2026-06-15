package com.example.nutriscan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nutriscan.ui.components.BottomNavigationBar
import com.example.nutriscan.ui.components.common.DateSelector
import com.example.nutriscan.ui.components.stats.CategoryStatsCard
import com.example.nutriscan.ui.components.stats.CommentStatsCard
import com.example.nutriscan.ui.components.stats.MinMaxStatsCard
import com.example.nutriscan.ui.components.stats.MonthlyLineChart
import com.example.nutriscan.ui.components.stats.ProgressStatsCard
import com.example.nutriscan.ui.components.stats.StatsOverviewCard
import com.example.nutriscan.ui.theme.getNutriAppColors
import com.example.nutriscan.ui.viewmodel.NutriViewModel
import kotlin.collections.isNotEmpty
import kotlin.collections.maxByOrNull
import kotlin.collections.minByOrNull

@Composable
fun StatsScreen(
    navController: NavHostController,
    viewModel: NutriViewModel
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val foodList by viewModel.todayFoodList.collectAsState()

    val totalCalories by viewModel.totalCalories.collectAsState()
    val totalProtein by viewModel.totalProtein.collectAsState()
    val totalCarbs by viewModel.totalCarbs.collectAsState()

    val goalCalories by viewModel.goalCalories.collectAsState()
    val goalProtein by viewModel.goalProtein.collectAsState()
    val goalCarbs by viewModel.goalCarbs.collectAsState()

    val monthlyCalorieStats by viewModel.monthlyCalorieStats.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    val appColors = getNutriAppColors(isDarkMode)

    val backgroundColor = appColors.background
    val primaryColor = appColors.primary
    val warningColor = appColors.warning
    val blueColor = appColors.blue
    val orangeColor = appColors.orange
    val cardBackground = appColors.card
    val textPrimary = appColors.textPrimary
    val textSecondary = appColors.textSecondary

    val totalRecords = foodList.size

    val averageCalories = if (foodList.isNotEmpty()) {
        totalCalories.toDouble() / foodList.size
    } else {
        0.0
    }

    val maxFood = foodList.maxByOrNull { it.calories }
    val minFood = foodList.minByOrNull { it.calories }

    val lowCalorieFoods = foodList.count { it.calories < 200 }
    val mediumCalorieFoods = foodList.count { it.calories in 200.0..499.0 }
    val highCalorieFoods = foodList.count { it.calories >= 500 }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentRoute = "stats_screen"
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
                        text = "Thống kê dinh dưỡng",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = textPrimary
                        )
                    )

                    Text(
                        text = "Tổng hợp dữ liệu theo ngày và theo tháng",
                        color = textSecondary
                    )
                }
            }

            item {
                DateSelector(
                    title = "Ngày thống kê",
                    selectedDate = selectedDate,
                    primaryColor = primaryColor,
                    cardBackground = cardBackground,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary,
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
                StatsOverviewCard(
                    selectedDate = selectedDate,
                    totalRecords = totalRecords,
                    totalCalories = totalCalories,
                    averageCalories = averageCalories,
                    primaryColor = primaryColor,
                    orangeColor = orangeColor,
                    blueColor = blueColor,
                    cardBackground = cardBackground,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }

            item {
                MonthlyLineChart(
                    stats = monthlyCalorieStats,
                    selectedDate = selectedDate,
                    primaryColor = primaryColor,
                    warningColor = warningColor,
                    cardBackground = cardBackground,
                    chartBackground = backgroundColor,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary,
                    gridColor = appColors.border,
                    axisColor = textSecondary
                )
            }

            item {
                ProgressStatsCard(
                    totalCalories = totalCalories,
                    totalProtein = totalProtein,
                    totalCarbs = totalCarbs,
                    goalCalories = goalCalories,
                    goalProtein = goalProtein,
                    goalCarbs = goalCarbs,
                    primaryColor = primaryColor,
                    warningColor = warningColor,
                    blueColor = blueColor,
                    cardBackground = cardBackground,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }

            item {
                MinMaxStatsCard(
                    foodList = foodList,
                    maxFood = maxFood,
                    minFood = minFood,
                    primaryColor = primaryColor,
                    warningColor = warningColor,
                    cardBackground = cardBackground,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }

            item {
                CategoryStatsCard(
                    lowCalorieFoods = lowCalorieFoods,
                    mediumCalorieFoods = mediumCalorieFoods,
                    highCalorieFoods = highCalorieFoods,
                    primaryColor = primaryColor,
                    orangeColor = orangeColor,
                    warningColor = warningColor,
                    cardBackground = cardBackground,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }

            item {
                CommentStatsCard(
                    totalRecords = totalRecords,
                    totalCalories = totalCalories,
                    goalCalories = goalCalories,
                    primaryColor = primaryColor,
                    warningColor = warningColor,
                    cardBackground = cardBackground,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }

            item {
                Spacer(modifier = Modifier.height(88.dp))
            }
        }
    }
}