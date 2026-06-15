package com.example.nutriscan.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Trang chủ"
                )
            },
            label = {
                Text("Trang chủ")
            },
            selected = currentRoute == "home_screen",
            colors = bottomNavItemColors(),
            onClick = {
                if (currentRoute != "home_screen") {
                    navController.navigate("home_screen") {
                        popUpTo("home_screen") {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = "Nhật ký"
                )
            },
            label = {
                Text("Nhật ký")
            },
            selected = currentRoute == "diary_screen",
            colors = bottomNavItemColors(),
            onClick = {
                if (currentRoute != "diary_screen") {
                    navController.navigate("diary_screen") {
                        popUpTo("home_screen") {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = "Thống kê"
                )
            },
            label = {
                Text("Thống kê")
            },
            selected = currentRoute == "stats_screen",
            colors = bottomNavItemColors(),
            onClick = {
                if (currentRoute != "stats_screen") {
                    navController.navigate("stats_screen") {
                        popUpTo("home_screen") {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Thông tin"
                )
            },
            label = {
                Text("Thông tin")
            },
            selected = currentRoute == "profile_screen",
            colors = bottomNavItemColors(),
            onClick = {
                if (currentRoute != "profile_screen") {
                    navController.navigate("profile_screen") {
                        popUpTo("home_screen") {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}

@Composable
fun bottomNavItemColors() =
    NavigationBarItemDefaults.colors(
        selectedIconColor = MaterialTheme.colorScheme.primary,
        selectedTextColor = MaterialTheme.colorScheme.primary,
        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
        indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
    )