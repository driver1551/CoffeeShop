package com.example.sevenwindstest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sevenwindstest.navigation.AppNavGraph
import com.example.sevenwindstest.ui.common.LocationPermissionRequest
import com.example.sevenwindstest.ui.theme.SevenWindsTestTheme
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Yandex mapkit setup
        MapKitFactory.setApiKey("f88b28ed-6620-4bc2-9c30-b397664b8bbe")
        MapKitFactory.initialize(this.applicationContext)
        MapKitFactory.getInstance().onStart()

        setContent {
            LocationPermissionRequest()

            SevenWindsTestTheme {
                AppNavGraph()
            }
        }
    }
}
