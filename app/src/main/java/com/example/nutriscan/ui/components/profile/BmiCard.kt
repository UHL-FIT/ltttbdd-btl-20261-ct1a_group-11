package com.example.nutriscan.ui.components.profile

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
import androidx.compose.material.icons.filled.Favorite
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
fun BmiCard(
    bmiResult: Double,
    bmiStatus: String,
    goalCalories: Int,
    goalProtein: Double,
    goalCarbs: Double,
    primaryColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    orangeColor: Color
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
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(primaryColor.copy(alpha = 0.12f), CircleShape)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = primaryColor
                    )
                }

                Spacer(modifier = Modifier.padding(6.dp))

                Column {
                    Text(
                        text = "Chỉ số BMI",
                        fontWeight = FontWeight.Bold,
                        color = textPrimary
                    )

                    Text(
                        text = "Đánh giá thể trạng và mục tiêu",
                        color = textSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BmiMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "BMI",
                    value = if (bmiResult > 0.0) bmiResult.toString() else "--",
                    color = primaryColor,
                    textSecondary = textSecondary
                )

                BmiMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "Trạng thái",
                    value = bmiStatus,
                    color = orangeColor,
                    textSecondary = textSecondary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Mục tiêu calo: ${if (goalCalories > 0) "$goalCalories calo/ngày" else "Chưa có"}",
                color = textPrimary,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Đạm: ${goalProtein.toInt()}g | Carb: ${goalCarbs.toInt()}g",
                color = textSecondary
            )
        }
    }
}

@Composable
fun BmiMiniBox(
    modifier: Modifier,
    title: String,
    value: String,
    color: Color,
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
            Text(
                text = value,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )

            Text(
                text = title,
                color = textSecondary
            )
        }
    }
}