package com.example.nutriscan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nutriscan.data.local.entity.UserEntity

@Dao
interface UserDao {

    // Thêm mới hoặc cập nhật thông tin người dùng (Nếu id = 1 đã tồn tại thì nó sẽ ghi đè)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: UserEntity)

    // Lấy thông tin cá nhân của người dùng ra để tính toán BMI và Calories
    @Query("SELECT * FROM user_table WHERE id = 1")
    suspend fun getUser(): UserEntity?
}