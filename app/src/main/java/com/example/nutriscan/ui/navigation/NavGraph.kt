package com.example.nutriscan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nutriscan.ui.screens.DiaryScreen
import com.example.nutriscan.ui.screens.HomeScreen
import com.example.nutriscan.ui.screens.ProfileScreen
import com.example.nutriscan.ui.screens.SetupScreen
import com.example.nutriscan.ui.screens.StatsScreen
import com.example.nutriscan.ui.viewmodel.NutriViewModel

@Composable
fun NavGraph(
    viewModel: NutriViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("diary_screen") {
            DiaryScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("stats_screen") {
            StatsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("profile_screen") {
            ProfileScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable("setup_screen") {
            SetupScreen(
                viewModel = viewModel,
                onNavigateToHome = {
                    navController.navigate("home_screen") {
                        popUpTo("setup_screen") {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}