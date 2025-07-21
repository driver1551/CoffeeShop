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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenwindstest.data.dto.CartItem
import com.example.sevenwindstest.data.dto.CoffeeShopMenuItem

@Composable
fun ShoppingCartScreen(
    cartState: ShoppingCartUiState,
    onAddItem: (CoffeeShopMenuItem) -> Unit,
    onRemoveItem: (CoffeeShopMenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ваш заказ", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(cartState.items) { cartItem ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
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
                                Icon(Icons.Default.Remove, contentDescription = "Удалить 1 товар")
                            }

                            Text(
                                cartItem.quantity.toString(),
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )

                            IconButton(onClick = { onAddItem(cartItem.item) }) {
                                Icon(Icons.Default.Add, contentDescription = "Добавть 1 товар")
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
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text("Оплатить")
        }
    }
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
        onRemoveItem = {}
    )
}
