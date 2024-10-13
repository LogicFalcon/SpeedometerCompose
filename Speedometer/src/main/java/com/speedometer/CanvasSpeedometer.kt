package com.speedometer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProtectionMeter(
    modifier: Modifier = Modifier,
    inputValue: Float,
    trackColor: Color = Color(0xFFE0E0E0),
    progressColors: List<Color>,
    innerGradient: Color,
    percentageColor: Color = Color.White,
    needleColor: Color = Color.Blue
) {

    val meterValue = getMeterValue(inputValue)
    Box(modifier = modifier.size(196.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val sweepAngle = 240f
            val fillSwipeAngle = (meterValue / 100f) * sweepAngle
            val height = size.height
            val width = size.width
            val startAngle = 150f
            val arcHeight = height - 20.dp.toPx()

            //UnFill Arc
            drawArc(
                color = trackColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset((width - height + 60f) / 2f, (height - arcHeight) / 2f),
                size = Size(arcHeight, arcHeight),
                style = Stroke(width = 50f, cap = StrokeCap.Round)
            )

            //Filled Arc
            drawArc(
                brush = Brush.horizontalGradient(progressColors),
                startAngle = startAngle,
                sweepAngle = fillSwipeAngle,
                useCenter = false,
                topLeft = Offset((width - height + 60f) / 2f, (height - arcHeight) / 2),
                size = Size(arcHeight, arcHeight),
                style = Stroke(width = 50f, cap = StrokeCap.Round)
            )

            // Needle Circle
            val centerOffset = Offset(width / 2f, height / 2.09f)

            drawCircle(
                Brush.radialGradient(
                    listOf(
                        innerGradient.copy(alpha = 0.2f),
                        Color.Transparent
                    )
                ), width / 2f
            )

            // Needle
            // Calculate needle angle based on inputValue
            val needleAngle = (meterValue / 100f) * sweepAngle + startAngle
            val needleLength = 160f // Adjust this value to control needle length
            val needleBaseWidth = 15f // Adjust this value to control the base width


            val needlePath = Path().apply {
                // Calculate the center top point of the needle
                val topCenterX = centerOffset.x + needleLength * cos(
                    Math.toRadians(needleAngle.toDouble()).toFloat()
                )
                val topCenterY = centerOffset.y + needleLength * sin(
                    Math.toRadians(needleAngle.toDouble()).toFloat()
                )

                // Calculate the top left and top right points of the needle
                val topLeftX = topCenterX + needleBaseWidth * cos(
                    Math.toRadians((needleAngle - 40).toDouble()).toFloat()
                )
                val topLeftY = topCenterY + needleBaseWidth * sin(
                    Math.toRadians((needleAngle - 40).toDouble()).toFloat()
                )
                val topRightX = topCenterX + needleBaseWidth * cos(
                    Math.toRadians((needleAngle + 40).toDouble()).toFloat()
                )
                val topRightY = topCenterY + needleBaseWidth * sin(
                    Math.toRadians((needleAngle + 40).toDouble()).toFloat()
                )

                // Calculate the base left and base right points of the needle
                val baseLeftX = centerOffset.x + needleBaseWidth * cos(
                    Math.toRadians((needleAngle - 90).toDouble()).toFloat()
                )
                val baseLeftY = centerOffset.y + needleBaseWidth * sin(
                    Math.toRadians((needleAngle - 90).toDouble()).toFloat()
                )
                val baseRightX = centerOffset.x + needleBaseWidth * cos(
                    Math.toRadians((needleAngle + 90).toDouble()).toFloat()
                )
                val baseRightY = centerOffset.y + needleBaseWidth * sin(
                    Math.toRadians((needleAngle + 90).toDouble()).toFloat()
                )

                // Create the needle path
                moveTo(topLeftX, topLeftY)      // Move to top left
                lineTo(baseLeftX, baseLeftY)    // Draw line to base left
                lineTo(baseRightX, baseRightY)  // Draw line to base right
                lineTo(topRightX, topRightY)    // Draw line to top right
                close()                         // Close the path
            }

            drawPath(
                color = needleColor,
                path = needlePath
            )

            drawCircle(Color.Cyan, 24f, centerOffset)
        }

        Column(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "${inputValue.toInt()} %", fontSize = 20.sp, lineHeight = 28.sp, color = percentageColor, fontWeight = FontWeight.Bold)
        }

    }
}

private fun getMeterValue(inputPercentage: Float): Float {
    return if (inputPercentage < 0) {
        0f
    } else if (inputPercentage > 100) {
        100f
    } else {
        inputPercentage
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ProtectionMeter(
        modifier = Modifier,
        inputValue = 0f,
        trackColor = Color.Gray,
        progressColors = listOf(Color.Green, Color.Blue),
        innerGradient = Color.White,
        percentageColor = Color.Black,
        needleColor = Color.Blue
    )
}

