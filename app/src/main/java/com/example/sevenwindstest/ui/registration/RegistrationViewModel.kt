package com.example.sevenwindstest.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstest.domain.usecase.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    private val _navigateToLogin = MutableSharedFlow<Unit>()
    val navigateToLogin: SharedFlow<Unit> = _navigateToLogin.asSharedFlow()


    fun onEmailChange(newEmail: String) {
        _uiState.setEmail(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.setPassword(newPassword)
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        _uiState.setRepeatPassword(newRepeatPassword)
    }

    fun onRegisterClick() {
        viewModelScope.launch {
            _uiState.setLoading(true)
            val result = registrationUseCase.invoke(_uiState.value.email, _uiState.value.password)
            if (result.isSuccess) {
                _navigateToLogin.emit(Unit)
            } else {
                _uiState.setErrorMessage(result.toString())
                _navigateToLogin.emit(Unit)
            }
            _uiState.setLoading(false)
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _navigateToLogin.emit(Unit)
        }
    }
}
