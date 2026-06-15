package com.example.nutriscan.ui.components.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun CategoryStatsCard(
    lowCalorieFoods: Int,
    mediumCalorieFoods: Int,
    highCalorieFoods: Int,
    primaryColor: Color,
    orangeColor: Color,
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
                text = "Phân loại theo nhóm calo",
                fontWeight = FontWeight.Bold,
                color = textPrimary
            )

            Spacer(modifier = Modifier.padding(6.dp))

            CategoryRow(
                title = "Nhóm ít calo",
                subtitle = "Dưới 200 calo",
                count = lowCalorieFoods,
                color = primaryColor,
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CategoryRow(
                title = "Nhóm trung bình",
                subtitle = "Từ 200 đến 499 calo",
                count = mediumCalorieFoods,
                color = orangeColor,
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Spacer(modifier = Modifier.padding(5.dp))

            CategoryRow(
                title = "Nhóm nhiều calo",
                subtitle = "Từ 500 calo trở lên",
                count = highCalorieFoods,
                color = warningColor,
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )
        }
    }
}

@Composable
fun CategoryRow(
    title: String,
    subtitle: String,
    count: Int,
    color: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = textPrimary
            )

            Text(
                text = subtitle,
                color = textSecondary
            )
        }

        Text(
            text = "$count món",
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}