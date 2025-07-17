package com.example.sevenwindstest.ui.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistrationRoute(
    onNavigateToLogin: () -> Unit
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    RegistrationScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onRegisterClick = viewModel::onRegisterClick,
        onNavigateToLogin = onNavigateToLogin
    )
}
