package com.example.nutriscan.ui.components.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nutriscan.data.local.entity.FoodDiaryEntity

@Composable
fun MinMaxStatsCard(
    foodList: List<FoodDiaryEntity>,
    maxFood: FoodDiaryEntity?,
    minFood: FoodDiaryEntity?,
    primaryColor: Color,
    warningColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color
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
            Text(
                text = "Min / Max",
                fontWeight = FontWeight.Bold,
                color = textPrimary
            )

            Spacer(modifier = Modifier.padding(6.dp))

            if (foodList.isEmpty()) {
                Text(
                    text = "Chưa có dữ liệu để thống kê.",
                    color = textSecondary
                )
            } else {
                MinMaxRow(
                    title = "Món nhiều calo nhất",
                    food = maxFood,
                    color = warningColor,
                    textSecondary = textSecondary
                )

                Spacer(modifier = Modifier.padding(5.dp))

                MinMaxRow(
                    title = "Món ít calo nhất",
                    food = minFood,
                    color = primaryColor,
                    textSecondary = textSecondary
                )
            }
        }
    }
}

@Composable
fun MinMaxRow(
    title: String,
    food: FoodDiaryEntity?,
    color: Color,
    textSecondary: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .background(color.copy(alpha = 0.18f), CircleShape)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.padding(6.dp))

        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = color
            )

            Text(
                text = if (food != null) {
                    "${food.foodName} - ${food.calories.toInt()} kcal"
                } else {
                    "Chưa có dữ liệu"
                },
                color = textSecondary
            )
        }
    }
}