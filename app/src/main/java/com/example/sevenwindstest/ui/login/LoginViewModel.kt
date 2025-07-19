package com.example.sevenwindstest.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstest.domain.usecase.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _navigateToCoffee = MutableSharedFlow<Unit>()
    val navigateToCoffee: SharedFlow<Unit> = _navigateToCoffee.asSharedFlow()

    init {
        _uiState.setEmail("driver")
        _uiState.setPassword("driver")
    }

    fun onLoginChange(newEmail: String) {
        _uiState.setEmail(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.setPassword(newPassword)
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _uiState.setLoading(true)
            val result = loginUseCase.invoke(_uiState.value.email, _uiState.value.password)
            if (result.isSuccess) {
                _navigateToCoffee.emit(Unit)
            } else {
                _uiState.setErrorMessage(result.toString())
                _navigateToCoffee.emit(Unit)
            }
            _uiState.setLoading(false)
        }
    }
}