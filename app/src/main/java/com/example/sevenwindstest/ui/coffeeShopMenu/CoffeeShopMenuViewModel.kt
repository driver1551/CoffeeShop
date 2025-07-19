package com.example.sevenwindstest.ui.coffeeShopMenu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoffeeShopMenuViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val coffeeShopId: Int = checkNotNull(savedStateHandle["id"])

    private val _uiState = MutableStateFlow(CoffeeShopMenuUiState(coffeeShopId = coffeeShopId))
    val uiState = _uiState.asStateFlow()


}