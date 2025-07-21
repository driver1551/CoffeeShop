package com.example.sevenwindstest.ui.coffeeShopMenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sevenwindstest.data.dto.CoffeeShopMenuItem
import com.example.sevenwindstest.ui.shoppingCart.ShoppingCartUiState

@Composable
fun CoffeeShopMenuScreen(
    uiState: CoffeeShopMenuUiState,
    cartState: ShoppingCartUiState,
    onToShoppingCartClick: () -> Unit,
    onAddItem: (CoffeeShopMenuItem) -> Unit,
    onRemoveItem: (CoffeeShopMenuItem) -> Unit
) {
    when {
        uiState.isLoading -> {
            IsLoading()
        }

        uiState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.errorMessage, color = Color.Red)
            }
        }

        uiState.coffeeShopMenuItemList == null -> {
            IsLoading()
        }

        uiState.coffeeShopMenuItemList.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Товары не найдены", color = Color.Red)
            }
        }

        else -> {
            Column {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.coffeeShopMenuItemList) { coffeeShopMenu ->
                        val quantity = cartState.items
                            .find { it.item.id == coffeeShopMenu.id }
                            ?.quantity ?: 0

                        MenuItem(
                            coffeeShopMenuItem = coffeeShopMenu,
                            quantity = quantity,
                            onAddItem = onAddItem,
                            onRemoveItem = onRemoveItem
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onToShoppingCartClick
                ) {
                    Text("Перейти к оплате")
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    coffeeShopMenuItem: CoffeeShopMenuItem,
    quantity: Int,
    onAddItem: (CoffeeShopMenuItem) -> Unit,
    onRemoveItem: (CoffeeShopMenuItem) -> Unit
) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = coffeeShopMenuItem.imageURL,
                contentDescription = "Изображение товара",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = coffeeShopMenuItem.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${coffeeShopMenuItem.price} руб",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = {
                            if (quantity > 0) onRemoveItem(coffeeShopMenuItem)
                        },
                        enabled = quantity > 0
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = "Уменьшить")
                    }

                    Text(text = quantity.toString())

                    IconButton(onClick = {
                        onAddItem(coffeeShopMenuItem)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Увеличить")
                    }
                }
            }
        }
    }
}

@Composable
fun IsLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview(showBackground = true)
fun CoffeeShopMenuPreview() {
    MenuItem(
        CoffeeShopMenuItem(
            id = 0,
            name = "Эспрессо",
            imageURL = "https://postmania.ru//files/products/0215-a.800x600.jpg",
            price = 100L
        ),
        onAddItem = {},
        onRemoveItem = {},
        quantity = 1
    )
}

@Composable
@Preview(showBackground = true)
fun CoffeeShopMenuScreenPreview() {
    CoffeeShopMenuScreen(
        uiState = CoffeeShopMenuUiState(
            coffeeShopMenuItemList = listOf(
                CoffeeShopMenuItem(
                    id = 0,
                    name = "Эспрессо",
                    imageURL = "https://postmania.ru//files/products/0215-a.800x600.jpg",
                    price = 100L
                ),
                CoffeeShopMenuItem(
                    id = 1,
                    name = "Латте",
                    imageURL = "https://gardman.ua/image/cache/catalog/blog/polza-kofe-1080x720.jpg",
                    price = 100L
                ),
            )
        ),
        onToShoppingCartClick = {},
        onAddItem = {},
        onRemoveItem = {},
        cartState = ShoppingCartUiState()
    )
}
