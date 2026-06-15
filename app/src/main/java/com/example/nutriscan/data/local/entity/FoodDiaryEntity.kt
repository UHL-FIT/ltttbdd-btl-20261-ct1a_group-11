package com.example.nutriscan.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_diary")
data class FoodDiaryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val foodName: String,
    val mealType: String = "",

    val gram: Double = 100.0,

    val calories: Double,
    val protein: Double,
    val carbs: Double,

    val date: String
)