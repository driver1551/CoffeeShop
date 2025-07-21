package com.example.sevenwindstest.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginRoute(
    onNavigateToCoffeeList: () -> Unit,
    navController: NavController = rememberNavController()
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navigateToCoffeeList by rememberUpdatedState(onNavigateToCoffeeList)

    LaunchedEffect(UInt) {
        viewModel.navigateToCoffee.collect {
            navigateToCoffeeList()
        }
    }

    LoginScreen(
        uiState = uiState,
        onLoginChange = viewModel::onLoginChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onBackClick = {navController.popBackStack()}
    )
}