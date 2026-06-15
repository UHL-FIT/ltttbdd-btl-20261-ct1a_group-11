package com.example.nutriscan.ui.components.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgressStatsCard(
    totalCalories: Int,
    totalProtein: Double,
    totalCarbs: Double,
    goalCalories: Int,
    goalProtein: Double,
    goalCarbs: Double,
    primaryColor: Color,
    warningColor: Color,
    blueColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    val calorieProgress = if (goalCalories > 0) {
        (totalCalories.toFloat() / goalCalories).coerceIn(0f, 1f)
    } else {
        0f
    }

    val proteinProgress = if (goalProtein > 0.0) {
        (totalProtein.toFloat() / goalProtein.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }

    val carbsProgress = if (goalCarbs > 0.0) {
        (totalCarbs.toFloat() / goalCarbs.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }

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
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Tiến độ so với mục tiêu",
                fontWeight = FontWeight.Bold,
                color = textPrimary
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgressStatRow(
                label = "Calo",
                current = totalCalories.toString(),
                goal = if (goalCalories > 0) goalCalories.toString() else "2000",
                unit = "calo",
                progress = calorieProgress,
                color = if (goalCalories > 0 && totalCalories > goalCalories) warningColor else primaryColor,
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgressStatRow(
                label = "Đạm",
                current = totalProtein.toInt().toString(),
                goal = if (goalProtein > 0) goalProtein.toInt().toString() else "50",
                unit = "g",
                progress = proteinProgress,
                color = Color(0xFFFF5252),
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgressStatRow(
                label = "Tinh bột",
                current = totalCarbs.toInt().toString(),
                goal = if (goalCarbs > 0) goalCarbs.toInt().toString() else "250",
                unit = "g",
                progress = carbsProgress,
                color = blueColor,
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )
        }
    }
}

@Composable
fun ProgressStatRow(
    label: String,
    current: String,
    goal: String,
    unit: String,
    progress: Float,
    color: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                fontWeight = FontWeight.SemiBold,
                color = textPrimary
            )

            Text(
                text = "$current/$goal $unit",
                color = textSecondary
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        LinearProgressIndicator(
            progress = {
                progress
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(9.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = color,
            trackColor = color.copy(alpha = 0.15f),
            strokeCap = StrokeCap.Round
        )
    }
}