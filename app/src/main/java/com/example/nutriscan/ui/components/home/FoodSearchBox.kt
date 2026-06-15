package com.example.nutriscan.ui.components.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FoodSearchBox(
    searchQuery: String,
    primaryColor: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    borderColor: Color,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(50.dp)),
        placeholder = {
            Text(
                text = "Tra cứu món ăn...",
                fontSize = 14.sp,
                color = textSecondary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = primaryColor
            )
        },
        shape = RoundedCornerShape(50.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = cardBackground,
            unfocusedContainerColor = cardBackground,
            focusedBorderColor = primaryColor,
            unfocusedBorderColor = borderColor,
            focusedTextColor = textPrimary,
            unfocusedTextColor = textPrimary,
            cursorColor = primaryColor
        ),
        singleLine = true
    )
}