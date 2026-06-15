package com.example.nutriscan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutriscan.data.local.AppDatabase
import com.example.nutriscan.ui.navigation.NavGraph
import com.example.nutriscan.ui.viewmodel.NutriViewModel
import com.example.nutriscan.ui.viewmodel.NutriViewModelFactory

class MainActivity : ComponentActivity() {

    private val lifecycleTag = "NUTRISCAN_LIFECYCLE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(lifecycleTag, "onCreate: Activity được tạo")

        setContent {
            val database = AppDatabase.getDatabase(applicationContext)

            val viewModel: NutriViewModel = viewModel(
                factory = NutriViewModelFactory(
                    userDao = database.userDao(),
                    foodDiaryDao = database.foodDiaryDao()
                )
            )

            val isDarkMode by viewModel.isDarkMode.collectAsState()

            val lightColors = lightColorScheme(
                primary = Color(0xFF2E7D32),
                background = Color(0xFFF1F8F4),
                surface = Color.White,
                onPrimary = Color.White,
                onBackground = Color(0xFF1A1C1E),
                onSurface = Color(0xFF1A1C1E)
            )

            val darkColors = darkColorScheme(
                primary = Color(0xFF81C784),
                background = Color(0xFF101510),
                surface = Color(0xFF1B241B),
                onPrimary = Color.Black,
                onBackground = Color.White,
                onSurface = Color.White
            )

            MaterialTheme(
                colorScheme = if (isDarkMode) darkColors else lightColors
            ) {
                NavGraph(viewModel = viewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(lifecycleTag, "onStart: Activity bắt đầu hiển thị")
    }

    override fun onResume() {
        super.onResume()
        Log.d(lifecycleTag, "onResume: Activity sẵn sàng tương tác")
    }

    override fun onPause() {
        super.onPause()
        Log.d(lifecycleTag, "onPause: Activity tạm dừng")
    }

    override fun onStop() {
        super.onStop()
        Log.d(lifecycleTag, "onStop: Activity không còn hiển thị")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(lifecycleTag, "onDestroy: Activity bị hủy")
    }
}