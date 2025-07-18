package com.example.sevenwindstest.ui.registration

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

data class RegistrationUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val errorMessage: String? = null
)

fun MutableStateFlow<RegistrationUiState>.setLoading(isLoading: Boolean) {
    update { it.copy(isLoading = isLoading) }
}

fun MutableStateFlow<RegistrationUiState>.setEmail(email: String) {
    update { it.copy(email = email) }
}

fun MutableStateFlow<RegistrationUiState>.setPassword(password: String) {
    update { it.copy(password = password) }
}

fun MutableStateFlow<RegistrationUiState>.setRepeatPassword(repeatPassword: String) {
    update { it.copy(repeatPassword = repeatPassword) }
}

fun MutableStateFlow<RegistrationUiState>.setErrorMessage(errorMessage: String) {
    update { it.copy(errorMessage = errorMessage) }
}