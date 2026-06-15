package com.example.nutriscan.ui.components.stats

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nutriscan.ui.viewmodel.DailyCalorieStat

@Composable
fun MonthlyLineChart(
    stats: List<DailyCalorieStat>,
    selectedDate: String,
    primaryColor: Color,
    warningColor: Color,
    cardBackground: Color,
    chartBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    gridColor: Color,
    axisColor: Color
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
                text = "Biểu đồ biến động calo theo tháng",
                fontWeight = FontWeight.Bold,
                color = textPrimary
            )

            Text(
                text = "Trục ngang: ngày trong tháng | Trục dọc: tổng calo (calo)",
                color = textSecondary
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (stats.isEmpty()) {
                Text(
                    text = "Chưa có dữ liệu trong tháng này.",
                    color = textSecondary
                )
            } else {
                LineChartCanvas(
                    stats = stats,
                    primaryColor = primaryColor,
                    warningColor = warningColor,
                    chartBackground = chartBackground,
                    textSecondary = textSecondary,
                    gridColor = gridColor,
                    axisColor = axisColor
                )
            }
        }
    }
}

@Composable
fun LineChartCanvas(
    stats: List<DailyCalorieStat>,
    primaryColor: Color,
    warningColor: Color,
    chartBackground: Color,
    textSecondary: Color,
    gridColor: Color,
    axisColor: Color
) {
    val goalCalories = 2000
    val maxDataCalories = stats.maxOfOrNull { it.calories } ?: 0
    val maxCalories = maxOf(maxDataCalories, goalCalories, 1)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(chartBackground, RoundedCornerShape(18.dp))
            .padding(8.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val leftPadding = 52f
        val rightPadding = 20f
        val topPadding = 24f
        val bottomPadding = 42f

        val chartWidth = canvasWidth - leftPadding - rightPadding
        val chartHeight = canvasHeight - topPadding - bottomPadding

        val textArgb = textSecondary.toArgbCompat()

        val textPaint = android.graphics.Paint().apply {
            color = textArgb
            textSize = 20f
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
        }

        val yTextPaint = android.graphics.Paint().apply {
            color = textArgb
            textSize = 20f
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.RIGHT
        }

        val smallTextPaint = android.graphics.Paint().apply {
            color = textArgb
            textSize = 18f
            isAntiAlias = true
            textAlign = android.graphics.Paint.Align.CENTER
        }

        fun getX(day: Int): Float {
            val safeDay = day.coerceIn(1, 31)
            return leftPadding + ((safeDay - 1).toFloat() / 30f) * chartWidth
        }

        fun getY(calories: Int): Float {
            return topPadding + chartHeight -
                    (calories.toFloat() / maxCalories.toFloat()) * chartHeight
        }

        val ySteps = 4

        for (i in 0..ySteps) {
            val y = topPadding + chartHeight * i / ySteps
            val value = maxCalories - (maxCalories * i / ySteps)

            drawLine(
                color = gridColor,
                start = Offset(leftPadding, y),
                end = Offset(canvasWidth - rightPadding, y),
                strokeWidth = 1.2f
            )

            drawContext.canvas.nativeCanvas.drawText(
                value.toString(),
                leftPadding - 8f,
                y + 6f,
                yTextPaint
            )
        }

        drawLine(
            color = axisColor,
            start = Offset(leftPadding, topPadding),
            end = Offset(leftPadding, canvasHeight - bottomPadding),
            strokeWidth = 2f
        )

        drawLine(
            color = axisColor,
            start = Offset(leftPadding, canvasHeight - bottomPadding),
            end = Offset(canvasWidth - rightPadding, canvasHeight - bottomPadding),
            strokeWidth = 2f
        )

        val goalY = getY(goalCalories)

        drawLine(
            color = warningColor.copy(alpha = 0.35f),
            start = Offset(leftPadding, goalY),
            end = Offset(canvasWidth - rightPadding, goalY),
            strokeWidth = 2.5f
        )

        drawContext.canvas.nativeCanvas.drawText(
            "2000",
            leftPadding - 8f,
            goalY + 6f,
            yTextPaint
        )

        val xLabels = listOf(1, 5, 10, 15, 20, 25, 30)

        xLabels.forEach { day ->
            val x = getX(day)

            drawLine(
                color = gridColor,
                start = Offset(x, topPadding),
                end = Offset(x, canvasHeight - bottomPadding),
                strokeWidth = 0.8f
            )

            drawContext.canvas.nativeCanvas.drawText(
                day.toString(),
                x,
                canvasHeight - bottomPadding + 26f,
                textPaint
            )
        }

        val sortedStats = stats.sortedBy { it.day }

        if (sortedStats.size == 1) {
            val item = sortedStats.first()
            val x = getX(item.day)
            val y = getY(item.calories)

            drawCircle(
                color = if (item.calories > goalCalories) warningColor else primaryColor,
                radius = 7f,
                center = Offset(x, y)
            )

            drawContext.canvas.nativeCanvas.drawText(
                "${item.calories}",
                x,
                (y - 12f).coerceAtLeast(topPadding + 12f),
                smallTextPaint
            )
        } else {
            val path = Path()

            sortedStats.forEachIndexed { index, item ->
                val x = getX(item.day)
                val y = getY(item.calories)

                if (index == 0) {
                    path.moveTo(x, y)
                } else {
                    path.lineTo(x, y)
                }
            }

            drawPath(
                path = path,
                color = primaryColor,
                style = Stroke(width = 4f)
            )

            sortedStats.forEach { item ->
                val x = getX(item.day)
                val y = getY(item.calories)

                drawCircle(
                    color = if (item.calories > goalCalories) warningColor else primaryColor,
                    radius = 6f,
                    center = Offset(x, y)
                )
            }

            val firstItem = sortedStats.first()
            val lastItem = sortedStats.last()

            drawContext.canvas.nativeCanvas.drawText(
                firstItem.calories.toString(),
                getX(firstItem.day),
                (getY(firstItem.calories) - 12f).coerceAtLeast(topPadding + 12f),
                smallTextPaint
            )

            if (lastItem.day != firstItem.day) {
                drawContext.canvas.nativeCanvas.drawText(
                    lastItem.calories.toString(),
                    getX(lastItem.day),
                    (getY(lastItem.calories) - 12f).coerceAtLeast(topPadding + 12f),
                    smallTextPaint
                )
            }
        }
    }
}

fun Color.toArgbCompat(): Int {
    return android.graphics.Color.argb(
        (alpha * 255).toInt(),
        (red * 255).toInt(),
        (green * 255).toInt(),
        (blue * 255).toInt()
    )
}