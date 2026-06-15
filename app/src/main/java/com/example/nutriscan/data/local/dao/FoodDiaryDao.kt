package com.example.nutriscan.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.nutriscan.data.local.entity.FoodDiaryEntity

@Dao
interface FoodDiaryDao {

    @Insert
    suspend fun insertFood(food: FoodDiaryEntity)

    @Update
    suspend fun updateFood(food: FoodDiaryEntity)

    @Delete
    suspend fun deleteFood(food: FoodDiaryEntity)

    @Query("SELECT * FROM food_diary WHERE date = :date")
    suspend fun getFoodsByDate(date: String): List<FoodDiaryEntity>

    @Query("SELECT * FROM food_diary")
    suspend fun getAllFoods(): List<FoodDiaryEntity>
}