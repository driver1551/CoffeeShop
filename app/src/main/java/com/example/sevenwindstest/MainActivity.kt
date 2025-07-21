package com.example.sevenwindstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.example.sevenwindstest.navigation.AppNavGraph
import com.example.sevenwindstest.ui.theme.SevenWindsTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SevenWindsTestTheme {
                AppNavGraph()
            }
        }
    }
}
