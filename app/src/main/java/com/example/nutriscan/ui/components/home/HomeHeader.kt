package com.example.nutriscan.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.nutriscan.data.local.entity.UserEntity

@Composable
fun HomeHeader(
    navController: NavHostController,
    currentUser: UserEntity?,
    gradientPrimary: Brush,
    textPrimary: Color,
    textSecondary: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Xin chào, 👋",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = textSecondary
                )
            )

            Text(
                text = if (!currentUser?.name.isNullOrBlank()) {
                    currentUser!!.name
                } else {
                    "Thành viên"
                },
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = textPrimary
                )
            )
        }

        Box(
            modifier = Modifier
                .size(46.dp)
                .background(gradientPrimary, CircleShape)
                .clickable {
                    navController.navigate("profile_screen")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (!currentUser?.name.isNullOrBlank()) {
                    currentUser!!.name.take(1).uppercase()
                } else {
                    "N"
                },
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}