package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                val viewModel = CalculatorViewModel()
                val buttonSpacing = 8.dp
                CalculatorScreen(
                    viewModel = viewModel,
                    buttonSpacing = buttonSpacing
                )
            }
        }
    }
}