package com.example.sevenwindstest.ui.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _uiState.update { it.copy(repeatPassword = newRepeatPassword) }
    }

    fun onRegisterClick() {
        // Пока заглушка, позже вызовем Repository
        println("Регистрация: ${_uiState.value.email}, ${_uiState.value.password}")
    }
}
