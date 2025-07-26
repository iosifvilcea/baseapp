package com.blankthings.baseapp.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.data.AuthRepository
import com.blankthings.baseapp.navigation.AppState
import com.blankthings.baseapp.ui.home.navigateToHome
import com.blankthings.baseapp.utils.ErrorType
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

@Serializable object LoginRoute

fun NavController.navigateToLogin() {
    navigate(LoginRoute) {
        popUpTo<LoginRoute>()
        launchSingleTop = true
    }
}

fun NavGraphBuilder.loginScreen(
    authRepository: AuthRepository,
    appState: AppState,
    onShowSnackBar: (ErrorType, String) -> Unit
) {
    composable<LoginRoute> {
        val viewModel: LoginViewModel = viewModel(
            factory = LoginViewModel.provideFactory(authRepository)
        )
        LoginRoute(
            loginViewModel = viewModel,
            appState = appState,
            onShowSnackBar = onShowSnackBar
        )
    }
}

@Composable
fun LoginRoute(
    loginViewModel: LoginViewModel,
    appState: AppState,
    onShowSnackBar: (ErrorType, String) -> Unit
) {
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