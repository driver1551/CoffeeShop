package com.example.sevenwindstest.ui.shoppingCart

import androidx.lifecycle.ViewModel
import com.example.sevenwindstest.data.dto.CartItem
import com.example.sevenwindstest.data.dto.CoffeeShopMenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ShoppingCartUiState())
    val uiState: StateFlow<ShoppingCartUiState> = _uiState.asStateFlow()

    fun addItem(item: CoffeeShopMenuItem) {
        _uiState.update { currentState ->
            val updatedList = currentState.items.toMutableList()
            val index = updatedList.indexOfFirst { it.item.id == item.id }

            if (index != -1) {
                val existing = updatedList[index]
                updatedList[index] = existing.copy(quantity = existing.quantity + 1)
            } else {
                updatedList.add(CartItem(item, 1))
            }

            currentState.copy(items = updatedList)
        }
    }

    fun removeItem(item: CoffeeShopMenuItem) {
        _uiState.update { currentState ->
            val updatedList = currentState.items.toMutableList()
            val index = updatedList.indexOfFirst { it.item.id == item.id }

            if (index != -1) {
                val existing = updatedList[index]
                if (existing.quantity > 1) {
                    updatedList[index] = existing.copy(quantity = existing.quantity - 1)
                } else {
                    updatedList.removeAt(index)
                }
            }

            currentState.copy(items = updatedList)
        }
    }
}
