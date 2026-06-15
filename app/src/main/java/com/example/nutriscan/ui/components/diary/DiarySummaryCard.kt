package com.example.nutriscan.ui.components.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DiarySummaryCard(
    totalCalories: Int,
    totalProtein: Double,
    totalCarbs: Double,
    primaryColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    orangeColor: Color,
    blueColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackground
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = null,
                    tint = primaryColor
                )

                Text(
                    text = "Tổng dinh dưỡng trong ngày",
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    color = textPrimary
                )
            }

            Text(
                text = "Tổng hợp calo, đạm và tinh bột từ các món đã lưu",
                color = textSecondary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DiaryMiniSummaryBox(
                    modifier = Modifier.weight(1f),
                    title = "Calo",
                    value = totalCalories.toString(),
                    unit = "calo",
                    color = primaryColor,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )

                DiaryMiniSummaryBox(
                    modifier = Modifier.weight(1f),
                    title = "Đạm",
                    value = totalProtein.toInt().toString(),
                    unit = "g",
                    color = orangeColor,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )

                DiaryMiniSummaryBox(
                    modifier = Modifier.weight(1f),
                    title = "Carb",
                    value = totalCarbs.toInt().toString(),
                    unit = "g",
                    color = blueColor,
                    textPrimary = textPrimary,
                    textSecondary = textSecondary
                )
            }
        }
    }
}

@Composable
fun DiaryMiniSummaryBox(
    modifier: Modifier,
    title: String,
    value: String,
    unit: String,
    color: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.10f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(color.copy(alpha = 0.18f), CircleShape)
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = color,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                color = textPrimary
            )

            Text(
                text = unit,
                color = textSecondary
            )
        }
    }
}