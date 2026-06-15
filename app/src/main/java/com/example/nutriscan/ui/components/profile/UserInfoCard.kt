package com.example.nutriscan.ui.components.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nutriscan.data.local.entity.UserEntity

@Composable
fun UserInfoCard(
    currentUser: UserEntity?,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    dividerColor: Color
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
                text = "Thông tin người dùng",
                fontWeight = FontWeight.Bold,
                color = textPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoRow(
                label = "Họ tên",
                value = if (!currentUser?.name.isNullOrBlank()) currentUser!!.name else "Chưa nhập",
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = dividerColor
            )

            InfoRow(
                label = "Cân nặng",
                value = if ((currentUser?.weight ?: 0.0) > 0.0) "${currentUser!!.weight} kg" else "Chưa nhập",
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = dividerColor
            )

            InfoRow(
                label = "Chiều cao",
                value = if ((currentUser?.height ?: 0.0) > 0.0) "${currentUser!!.height} cm" else "Chưa nhập",
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = dividerColor
            )

            InfoRow(
                label = "Tuổi",
                value = if ((currentUser?.age ?: 0) > 0) "${currentUser!!.age}" else "Chưa nhập",
                textPrimary = textPrimary,
                textSecondary = textSecondary
            )
        }
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String,
    textPrimary: Color,
    textSecondary: Color
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = textSecondary
        )

        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
            color = textPrimary
        )
    }
}