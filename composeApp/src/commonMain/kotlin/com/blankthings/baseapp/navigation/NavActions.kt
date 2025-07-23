package com.blankthings.baseapp.navigation

import androidx.navigation.NavHostController
import com.blankthings.baseapp.model.Note
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable data object Splash: Routes
    @Serializable data object Login: Routes
    @Serializable data object CreateAccount: Routes
    @Serializable data object ForgotPassword: Routes

    @Serializable data object Authorized: Routes

    @Serializable data object HomeBase: Routes

    @Serializable data class Note(val noteId: Int): Routes

    @Serializable data object Account: Routes
    @Serializable data object Settings: Routes
}

class NavActions(val navHostController: NavHostController) {
    val navigateToForgotPassword: () -> Unit = {
        navHostController.navigate(Routes.ForgotPassword) {
            popUpTo<Routes.Login>()
            launchSingleTop = true
        }
    }

    val navigateToCreateAccount: () -> Unit = {
        navHostController.navigate(Routes.CreateAccount)
    }

    val navigateToLogin: () -> Unit = {
        navHostController.navigate(Routes.Login) {
            popUpTo<Routes.Login>()
            launchSingleTop = true
        }
    }

    val navigateToAuthorized: () -> Unit = {
        navHostController.navigate(Routes.Authorized)
    }

    val navigateToNote: (Int) -> Unit = {
        navHostController.navigate(Routes.Note(it)) {
            popUpTo<Routes.HomeBase> {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToSettings: () -> Unit = {
        navHostController.navigate(Routes.Settings) {
            popUpTo<Routes.Authorized> {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}