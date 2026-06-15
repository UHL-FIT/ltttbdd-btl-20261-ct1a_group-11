package com.example.nutriscan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nutriscan.ui.theme.getNutriAppColors
import com.example.nutriscan.ui.viewmodel.NutriViewModel

@Composable
fun SetupScreen(
    viewModel: NutriViewModel,
    onNavigateToHome: () -> Unit
) {
    val currentUser by viewModel.currentUser.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()
    val appColors = getNutriAppColors(isDarkMode)

    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(currentUser) {
        val user = currentUser

        if (user != null && user.weight > 0.0 && user.height > 0.0) {
            name = user.name
            weight = user.weight.toString()
            height = user.height.toString()
            age = if (user.age > 0) user.age.toString() else ""
        }
    }

    val isEditMode = currentUser != null &&
            (currentUser?.weight ?: 0.0) > 0.0 &&
            (currentUser?.height ?: 0.0) > 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appColors.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (isEditMode) {
                "Sửa thông tin cá nhân"
            } else {
                "Chào mừng đến với NutriScan"
            },
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = appColors.textPrimary
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nhập chỉ số để tính BMI và mục tiêu Calo",
            color = appColors.textSecondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { value ->
                name = value
                errorMessage = ""
            },
            label = {
                Text(text = "Họ tên")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = setupTextFieldColors(
                primaryColor = appColors.primary,
                containerColor = appColors.card,
                textColor = appColors.textPrimary,
                labelColor = appColors.textSecondary,
                borderColor = appColors.border
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { value ->
                age = value.filter { char ->
                    char.isDigit()
                }
                errorMessage = ""
            },
            label = {
                Text(text = "Tuổi")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = setupTextFieldColors(
                primaryColor = appColors.primary,
                containerColor = appColors.card,
                textColor = appColors.textPrimary,
                labelColor = appColors.textSecondary,
                borderColor = appColors.border
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = weight,
            onValueChange = { value ->
                weight = value.filter { char ->
                    char.isDigit() || char == '.'
                }
                errorMessage = ""
            },
            label = {
                Text(text = "Cân nặng (kg)")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = setupTextFieldColors(
                primaryColor = appColors.primary,
                containerColor = appColors.card,
                textColor = appColors.textPrimary,
                labelColor = appColors.textSecondary,
                borderColor = appColors.border
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { value ->
                height = value.filter { char ->
                    char.isDigit() || char == '.'
                }
                errorMessage = ""
            },
            label = {
                Text(text = "Chiều cao (cm)")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = setupTextFieldColors(
                primaryColor = appColors.primary,
                containerColor = appColors.card,
                textColor = appColors.textPrimary,
                labelColor = appColors.textSecondary,
                borderColor = appColors.border
            )
        )

        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = errorMessage,
                color = appColors.warning,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val nameInput = name.trim()
                val ageInput = age.trim()
                val weightInput = weight.trim()
                val heightInput = height.trim()

                val ageValue = ageInput.toIntOrNull()
                val weightValue = weightInput.toDoubleOrNull()
                val heightValue = heightInput.toDoubleOrNull()

                when {
                    nameInput.isBlank() ||
                            ageInput.isBlank() ||
                            weightInput.isBlank() ||
                            heightInput.isBlank() -> {
                        errorMessage = "Vui lòng nhập đầy đủ thông tin"
                    }

                    ageValue == null || ageValue < 1 || ageValue > 120 -> {
                        errorMessage = "Tuổi phải nằm trong khoảng từ 1 đến 120"
                    }

                    weightValue == null || weightValue < 1.0 || weightValue > 300.0 -> {
                        errorMessage = "Cân nặng phải nằm trong khoảng từ 1 đến 300 kg"
                    }

                    heightValue == null || heightValue < 50.0 || heightValue > 250.0 -> {
                        errorMessage = "Chiều cao phải nằm trong khoảng từ 50 đến 250 cm"
                    }

                    else -> {
                        errorMessage = ""

                        viewModel.saveUserProfile(
                            name = nameInput,
                            weight = weightValue,
                            height = heightValue,
                            age = ageValue
                        )

                        onNavigateToHome()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = appColors.primary,
                contentColor = if (isDarkMode) Color.Black else Color.White
            )
        ) {
            Text(
                text = if (isEditMode) {
                    "Cập nhật thông tin"
                } else {
                    "Bắt đầu tính toán Calo"
                },
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun setupTextFieldColors(
    primaryColor: Color,
    containerColor: Color,
    textColor: Color,
    labelColor: Color,
    borderColor: Color
) =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = primaryColor,
        unfocusedBorderColor = borderColor,
        focusedLabelColor = primaryColor,
        unfocusedLabelColor = labelColor,
        cursorColor = primaryColor,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        focusedTextColor = textColor,
        unfocusedTextColor = textColor
    )