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

    if (uiState is AuthUiState.Failure) {
        val errorMessage = stringResource((uiState as AuthUiState.Failure).errorType.message)
        LaunchedEffect(uiState) {
            snackbarHostState.showSnackbar(errorMessage)
        }
    }

    when (uiState) {
        is AuthUiState.Success -> navActions.navigateToAuthorized.invoke()
        else -> {
            loginScreen(
                uiState = uiState,
                loginViewModel = loginViewModel,
                navActions = navActions,
                showLoadingScreen = uiState is AuthUiState.Loading
            )
        }
    }
}

@Composable
fun loginScreen(
    uiState: AuthUiState,
    loginViewModel: LoginViewModel,
    navActions: NavActions,
    showLoadingScreen: Boolean = false) {
    LoginScreen(
        authUiState = uiState,
        onForgotAccountClicked = navActions.navigateToForgotPassword,
        onLoginClicked = loginViewModel::login,
        onCreateAccountClicked = navActions.navigateToCreateAccount,
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