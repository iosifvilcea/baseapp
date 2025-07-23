package com.blankthings.baseapp.ui.login

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.data.AuthRepository
import com.blankthings.baseapp.navigation.AppState
import com.blankthings.baseapp.utils.ErrorType
import kotlinx.serialization.Serializable

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