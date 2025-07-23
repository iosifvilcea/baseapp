package com.blankthings.baseapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blankthings.baseapp.ui.AppState
import com.blankthings.baseapp.ui.home.navigateToHome
import com.blankthings.baseapp.utils.ErrorType
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    appState: AppState,
    onShowSnackBar: (ErrorType, String) -> Unit
) {
    // TODO - Do not pass viewModel to the composable.
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = uiState) {
        is AuthUiState.Success -> {
            loginViewModel.reset()
            appState.navController.navigateToHome()
        }
        is AuthUiState.Failure -> {
            loginScreen(
                loginViewModel = loginViewModel,
                showLoadingScreen = false
            )
            val errorMessage = stringResource(currentState.errorType.message)
            onShowSnackBar.invoke(currentState.errorType, errorMessage)
        }
        else -> {
            loginScreen(
                loginViewModel = loginViewModel,
                showLoadingScreen = currentState is AuthUiState.Loading
            )
        }
    }
}

@Composable
fun loginScreen(
    loginViewModel: LoginViewModel,
    showLoadingScreen: Boolean = false
) {
    LoginScreen(onLoginClicked = loginViewModel::login)
    if (showLoadingScreen) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)), // Semi-transparent overlay
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}