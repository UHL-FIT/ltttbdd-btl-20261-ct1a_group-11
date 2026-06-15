package com.example.nutriscan.ui.components.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
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
fun StatsOverviewCard(
    selectedDate: String,
    totalRecords: Int,
    totalCalories: Int,
    averageCalories: Double,
    primaryColor: Color,
    orangeColor: Color,
    blueColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(26.dp)),
        shape = RoundedCornerShape(26.dp),
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
                        imageVector = Icons.Default.BarChart,
                        contentDescription = null,
                        tint = primaryColor
                    )
                }

                Spacer(modifier = Modifier.padding(6.dp))

                Column {
                    Text(
                        text = "Tổng quan trong ngày",
                        fontWeight = FontWeight.Bold,
                        color = textPrimary
                    )

                    Text(
                        text = selectedDate,
                        color = textSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                StatMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "Bản ghi",
                    value = totalRecords.toString(),
                    color = primaryColor,
                    textSecondary = textSecondary
                )

                StatMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "Tổng calo",
                    value = totalCalories.toString(),
                    color = orangeColor,
                    textSecondary = textSecondary
                )

                StatMiniBox(
                    modifier = Modifier.weight(1f),
                    title = "TB/món",
                    value = averageCalories.toInt().toString(),
                    color = blueColor,
                    textSecondary = textSecondary
                )
            }
        }
    }
}

@Composable
fun StatMiniBox(
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