package com.example.sevenwindstest.ui.coffeeshops

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstest.domain.usecase.GetCoffeeShopsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeShopListViewModel @Inject constructor(
    private val getCoffeeShopsUseCase: GetCoffeeShopsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoffeeShopListUiState())
    val uiState = _uiState.asStateFlow()

    private val _navigateToCoffeeShop = MutableSharedFlow<Unit>()
    val navigateToCoffeeShop: SharedFlow<Unit> = _navigateToCoffeeShop.asSharedFlow()

    init {
        viewModelScope.launch {
            _uiState.setLoading(true)
            val result = getCoffeeShopsUseCase.invoke()
            if (result.isSuccess) {
                _uiState.setCoffeeShopList(result.getOrNull())
            } else {
                _uiState.setErrorMessage("Что-то пошло не так ...")
            }
            _uiState.setLoading(false)
        }
    }

    fun onCoffeeShopClick() {
    }
}