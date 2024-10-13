package com.base.speedometer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.base.speedometer.ui.theme.SpeedometerTheme
import com.speedometer.ProtectionMeter


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedometerTheme {
                    Greeting()
            }
        }
    }

}

@Composable
fun Greeting() {
  MainUi()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
  MainUi()
}
@Composable
fun MainUi(){
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    SpeedometerTheme {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .align(androidx.compose.ui.Alignment.Center)) {
                ProtectionMeter(
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally),
                    inputValue = sliderPosition,
                    trackColor = Color.Gray,
                    progressColors = listOf(Color.Green, Color.Blue),
                    innerGradient = Color.White,
                    percentageColor = Color.Black,
                    needleColor = Color.Black
                )
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

            }

        }
    }
}



///




