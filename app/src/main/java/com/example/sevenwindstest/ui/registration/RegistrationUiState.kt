package com.example.sevenwindstest.ui.registration

data class RegistrationUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
