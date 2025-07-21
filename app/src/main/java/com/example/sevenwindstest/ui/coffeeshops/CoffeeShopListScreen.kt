package com.example.sevenwindstest.ui.coffeeshops

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sevenwindstest.data.dto.CoffeeShop
import com.example.sevenwindstest.data.dto.Point
import com.example.sevenwindstest.navigation.AppTopBar
import com.example.sevenwindstest.ui.common.AppElevatedCard
import com.example.sevenwindstest.ui.common.AppText
import com.example.sevenwindstest.ui.theme.SevenWindsTestTheme

@Composable
fun CoffeeShopListScreen(
    uiState: CoffeeShopListUiState,
    onCoffeeShopClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Ближайшие кофейни",
                canNavigateBack = true,
                onBackClick = onBackClick
            )
        }, content = { innerPadding ->
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = uiState.errorMessage, color = Color.Red)
                    }
                }

                uiState.coffeeShopList == null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Данные не найдены", color = Color.Red)
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.coffeeShopList) { coffeeShop ->
                            CoffeeShopCard(coffeeShop, onClick = onCoffeeShopClick)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CoffeeShopCard(
    coffeeShop: CoffeeShop,
    onClick: (Int) -> Unit
) {
    AppElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClick(coffeeShop.id) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AppText(
                text = coffeeShop.name,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            AppText(
                text = "х км от вас",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CoffeeShopListPreview() {
    val coffeeShopList = listOf(
        CoffeeShop(
            id = 1,
            name = "Кофешоп 1",
            point = Point(
                latitude = 9.21,
                longitude = 2.22
            )
        ),
        CoffeeShop(
            id = 2,
            name = "Кофешоп 2",
            point = Point(
                latitude = 1.12,
                longitude = 2.44
            )
        ),
        CoffeeShop(
            id = 3,
            name = "Кофешоп 3",
            point = Point(
                latitude = 1.12,
                longitude = 2.44
            )
        )
    )
    SevenWindsTestTheme {
        CoffeeShopListScreen(
            uiState = CoffeeShopListUiState(
                isLoading = false,
                errorMessage = null,
                coffeeShopList = coffeeShopList
            ),
            onCoffeeShopClick = {},
            onBackClick = {}
        )
    }
}