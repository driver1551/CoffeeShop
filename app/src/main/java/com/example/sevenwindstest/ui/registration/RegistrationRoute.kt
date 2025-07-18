package com.example.sevenwindstest.ui.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegistrationRoute(
    onNavigateToLogin: () -> Unit
) {
    val viewModel: RegistrationViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navigateToLogin by rememberUpdatedState(onNavigateToLogin)

    LaunchedEffect(Unit) {
        viewModel.navigateToLogin.collect {
            navigateToLogin()
        }
    }

    RegistrationScreen(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onRegisterClick = viewModel::onRegisterClick,
        onNavigateToLogin = onNavigateToLogin
    )
}
