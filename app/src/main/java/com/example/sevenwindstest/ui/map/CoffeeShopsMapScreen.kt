package com.example.sevenwindstest.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.viewinterop.AndroidView
import com.example.sevenwindstest.R
import com.example.sevenwindstest.domain.model.CoffeeShopWithDistance
import com.example.sevenwindstest.navigation.AppTopBar
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun CoffeeShopsMapScreen(
    coffeeShops: List<CoffeeShopWithDistance>,
    onBackClick: () -> Unit,
    onCoffeeShopClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Карта",
                canNavigateBack = true,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            factory = { context ->
                val mapView = MapView(context)
                val imageProvider = ImageProvider.fromResource(context, R.drawable.coffee_cup_32)
                val mapObjects = mapView.map.mapObjects

                // Центрируем карту на первую кофейню
                coffeeShops.firstOrNull()?.let { shop ->
                    val point =
                        Point(shop.coffeeShop.point.latitude, shop.coffeeShop.point.longitude)
                    mapView.map.move(
                        CameraPosition(point, 15.0f, 0f, 0f),
                        Animation(Animation.Type.SMOOTH, 1f),
                        null
                    )
                }

                val tapListener = MapObjectTapListener { mapObject, _ ->
                    val id = mapObject.userData as? Int
                    id?.let {
                        onCoffeeShopClick(it)
                    }
                    true
                }

                // Устанавливаем все кофени на карту
                coffeeShops.forEach { shopWithDistance ->
                    val coffeeShop = shopWithDistance.coffeeShop
                    val point = Point(coffeeShop.point.latitude, coffeeShop.point.longitude)

                    val placemark = mapObjects.addPlacemark(point).apply {
                        userData = coffeeShop.id
                        addTapListener(tapListener)
                        setIcon(imageProvider)
                        setText(coffeeShop.name)
                    }
                }

                mapView
            }
        )
    }
}
