package com.example.nutriscan.ui.components.profile

import androidx.compose.foundation.background
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
import com.example.nutriscan.data.local.entity.UserEntity

@Composable
fun ProfileHeader(
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
                text = "Thông tin cá nhân",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = textPrimary
                )
            )

            Text(
                text = "Quản lý chỉ số BMI và mục tiêu dinh dưỡng",
                color = textSecondary
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .background(gradientPrimary, CircleShape),
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
                fontSize = 20.sp
            )
        }
    }
}