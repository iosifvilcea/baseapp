package com.blankthings.baseapp.ui.account

import AccountScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.navigation.Routes
import kotlinx.serialization.Serializable

@Serializable data object AccountRoute

fun NavController.navigateToAccount() {
    navigate(AccountRoute) {
        popUpTo<Routes.Authorized> {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.accountScreen(
    onLogoutClicked: () -> Unit
) {
    composable<Routes.Account> {
        val viewModel: AccountViewModel = viewModel()
        AccountScreen(onLogoutClicked)
    }
}