package com.example.nutriscan.ui.components.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nutriscan.data.local.entity.FoodDiaryEntity

@Composable
fun EditFoodDialog(
    food: FoodDiaryEntity,
    primaryColor: Color,
    warningColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    onDismiss: () -> Unit,
    onSave: (FoodDiaryEntity, Double) -> Unit
) {
    var gramInput by remember(food.id) {
        mutableStateOf(
            if (food.gram % 1.0 == 0.0) {
                food.gram.toInt().toString()
            } else {
                food.gram.toString()
            }
        )
    }

    var gramErrorMessage by remember(food.id) {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        containerColor = cardBackground,
        title = {
            Text(
                text = "Sửa khối lượng",
                color = textPrimary
            )
        },
        text = {
            Column {
                Text(
                    text = "Nhập số gram mới cho món ăn",
                    color = textSecondary
                )

                Spacer(modifier = androidx.compose.ui.Modifier.height(12.dp))

                OutlinedTextField(
                    value = gramInput,
                    onValueChange = { value ->
                        gramInput = value.filter { char ->
                            char.isDigit() || char == '.'
                        }
                        gramErrorMessage = ""
                    },
                    label = {
                        Text(text = "Khối lượng (gram)")
                    },
                    singleLine = true
                )

                if (gramErrorMessage.isNotBlank()) {
                    Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))

                    Text(
                        text = gramErrorMessage,
                        color = warningColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newGram = gramInput.trim().toDoubleOrNull()

                    if (newGram == null || newGram <= 0.0) {
                        gramErrorMessage = "Bạn phải nhập số lớn hơn 0"
                        return@TextButton
                    }

                    onSave(food, newGram)
                }
            ) {
                Text(
                    text = "Lưu",
                    color = primaryColor
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(
                    text = "Hủy",
                    color = textSecondary
                )
            }
        }
    )
}