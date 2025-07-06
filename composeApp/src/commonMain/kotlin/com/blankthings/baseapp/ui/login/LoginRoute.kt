package com.blankthings.baseapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blankthings.baseapp.navigation.NavActions
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    navActions: NavActions,
    snackbarHostState: SnackbarHostState
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = uiState) {
        is AuthUiState.Success -> {
            loginViewModel.reset()
            navActions.navigateToAuthorized.invoke()
        }
        is AuthUiState.Failure -> {
            loginScreen(
                loginViewModel = loginViewModel,
                navActions = navActions,
                showLoadingScreen = false
            )

            val errorMessage = stringResource(currentState.errorType.message)
            LaunchedEffect(currentState.errorType) {
                snackbarHostState.showSnackbar(errorMessage)
            }
        }
        else -> {
            loginScreen(
                loginViewModel = loginViewModel,
                navActions = navActions,
                showLoadingScreen = currentState is AuthUiState.Loading
            )
        }
    }
}

@Composable
fun loginScreen(
    loginViewModel: LoginViewModel,
    navActions: NavActions,
    showLoadingScreen: Boolean = false
) {
    LoginScreen(
        onForgotAccountClicked = navActions.navigateToForgotPassword,
        onLoginClicked = loginViewModel::login,
        onCreateAccountClicked = navActions.navigateToCreateAccount,
        onBypassButtonClicked = navActions.navigateToSettings
    )
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