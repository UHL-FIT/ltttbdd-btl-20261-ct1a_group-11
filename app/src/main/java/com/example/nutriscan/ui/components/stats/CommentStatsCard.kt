package com.example.nutriscan.ui.components.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
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
fun CommentStatsCard(
    totalRecords: Int,
    totalCalories: Int,
    goalCalories: Int,
    primaryColor: Color,
    warningColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color
) {
    val isOverGoal = goalCalories > 0 && totalCalories > goalCalories

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
                Icon(
                    imageVector = if (isOverGoal) Icons.Default.Warning else Icons.Default.Star,
                    contentDescription = null,
                    tint = if (isOverGoal) warningColor else primaryColor
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = "Nhận xét",
                    fontWeight = FontWeight.Bold,
                    color = textPrimary
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = getNutritionComment(
                    totalRecords = totalRecords,
                    totalCalories = totalCalories,
                    goalCalories = goalCalories
                ),
                color = textSecondary
            )
        }
    }
}

fun getNutritionComment(
    totalRecords: Int,
    totalCalories: Int,
    goalCalories: Int
): String {
    return when {
        totalRecords == 0 -> {
            "Ngày này chưa có bản ghi nào. Hãy thêm món ăn ở trang chủ để theo dõi dinh dưỡng."
        }

        goalCalories > 0 && totalCalories > goalCalories -> {
            "Bạn đã vượt mục tiêu calo trong ngày. Nên cân đối lại khẩu phần ăn ở các bữa tiếp theo."
        }

        goalCalories > 0 && totalCalories < goalCalories * 0.5 -> {
            "Lượng calo hiện tại còn khá thấp so với mục tiêu. Bạn có thể bổ sung thêm thực phẩm phù hợp."
        }

        goalCalories > 0 -> {
            "Lượng calo trong ngày đang ở mức tương đối hợp lý so với mục tiêu."
        }

        else -> {
            "Dữ liệu đã được thống kê. Bạn có thể nhập thông tin cá nhân để app tính mục tiêu calo chính xác hơn."
        }
    }
}