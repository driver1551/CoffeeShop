package com.example.sevenwindstest.ui.shoppingCart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenwindstest.data.dto.CartItem
import com.example.sevenwindstest.data.dto.CoffeeShopMenuItem
import com.example.sevenwindstest.navigation.AppTopBar
import com.example.sevenwindstest.ui.common.AppButton
import com.example.sevenwindstest.ui.common.AppElevatedCard

@Composable
fun ShoppingCartScreen(
    cartState: ShoppingCartUiState,
    onAddItem: (CoffeeShopMenuItem) -> Unit,
    onRemoveItem: (CoffeeShopMenuItem) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Ваш заказ",
                canNavigateBack = true,
                onBackClick = onBackClick
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 4.dp)
                    .padding(vertical = 4.dp)
            ) {
                LazyColumn {
                    items(cartState.items) { cartItem ->
                        AppElevatedCard(modifier = Modifier.padding(vertical = 4.dp)) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(cartItem.item.name, fontWeight = FontWeight.Bold)
                                    Text(
                                        "${cartItem.item.price} руб",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { onRemoveItem(cartItem.item) }) {
                                        Icon(
                                            Icons.Default.Remove,
                                            contentDescription = "Удалить 1 товар"
                                        )
                                    }

                                    Text(
                                        cartItem.quantity.toString(),
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )

                                    IconButton(onClick = { onAddItem(cartItem.item) }) {
                                        Icon(
                                            Icons.Default.Add,
                                            contentDescription = "Добавть 1 товар"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Время ожидания заказа\n15 минут!\nСпасибо, что выбрали нас!",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color(0xFF83623F)
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {}
                ) {
                    Text("Оплатить")
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun ShoppingCartScreenPreview() {
    ShoppingCartScreen(
        cartState = ShoppingCartUiState(
            items = listOf(
                CartItem(
                    CoffeeShopMenuItem(
                        id = 1,
                        name = "Кофе 1",
                        imageURL = "",
                        price = 100L
                    ),
                    quantity = 2
                ),
                CartItem(
                    CoffeeShopMenuItem(
                        id = 2,
                        name = "Кофе 2",
                        imageURL = "",
                        price = 200L
                    ),
                    quantity = 4
                ),
                CartItem(
                    CoffeeShopMenuItem(
                        id = 2,
                        name = "Кофе 2",
                        imageURL = "",
                        price = 200L
                    ),
                    quantity = 4
                ),
                CartItem(
                    CoffeeShopMenuItem(
                        id = 2,
                        name = "Кофе 2",
                        imageURL = "",
                        price = 200L
                    ),
                    quantity = 4
                ),
            )
        ),
        onAddItem = {},
        onRemoveItem = {},
        onBackClick = {}
    )
}
