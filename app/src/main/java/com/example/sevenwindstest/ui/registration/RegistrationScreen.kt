package com.example.sevenwindstest.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sevenwindstest.navigation.AppTopBar
import com.example.sevenwindstest.ui.common.AppButton
import com.example.sevenwindstest.ui.common.AppOutlinedTextField
import com.example.sevenwindstest.ui.common.AppText
import com.example.sevenwindstest.ui.theme.SevenWindsTestTheme

@Composable
fun RegistrationScreen(
    uiState: RegistrationUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Регистрация",
                canNavigateBack = false
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                AppOutlinedTextField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    label = "Email",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppOutlinedTextField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    label = "Пароль",
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                AppOutlinedTextField(
                    value = uiState.repeatPassword,
                    onValueChange = onRepeatPasswordChange,
                    label = "Повторите пароль",
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    onClick = onRegisterClick,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isLoading
                ) {
                    Text("Зарегистрироваться")
                }

                TextButton(onClick = onLoginClick) {
                    AppText("Уже есть аккаунт? Войти", style = MaterialTheme.typography.bodyMedium)
                }

                if (uiState.errorMessage != null) {
                    Text(
                        text = uiState.errorMessage,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    SevenWindsTestTheme {
        RegistrationScreen(
            uiState = RegistrationUiState(
                email = "test@example.com",
                password = "123456",
                repeatPassword = "123456"
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onRepeatPasswordChange = {},
            onRegisterClick = {},
            onLoginClick = {}
        )
    }
}
