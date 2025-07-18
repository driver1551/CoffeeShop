package com.example.sevenwindstest.ui.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null
)

fun MutableStateFlow<LoginUiState>.setLoading(isLoading: Boolean) {
    update { it.copy(isLoading = isLoading) }
}

fun MutableStateFlow<LoginUiState>.setEmail(email: String) {
    update { it.copy(email = email) }
}

fun MutableStateFlow<LoginUiState>.setPassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<LoginUiState>.setErrorMessage(errorMessage: String) {
    update { it.copy(errorMessage = errorMessage) }
}