package com.blankthings.baseapp.ui.account

import AccountScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.ui.home.AuthorizedRoute
import kotlinx.serialization.Serializable

@Serializable object AccountRoute

fun NavController.navigateToAccount() {
    navigate(AccountRoute) {
        popUpTo<AuthorizedRoute> {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.accountScreen(
    onLogoutClicked: () -> Unit
) {
    composable<AccountRoute> {
        AccountScreen(onLogoutClicked)
    }
}