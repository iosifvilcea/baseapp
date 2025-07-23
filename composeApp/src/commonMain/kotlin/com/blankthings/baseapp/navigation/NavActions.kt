package com.blankthings.baseapp.navigation

import androidx.navigation.NavHostController
import com.blankthings.baseapp.model.Note
import com.blankthings.baseapp.ui.home.BaseHomeRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable object Splash: Routes
    @Serializable object Login: Routes
    @Serializable object CreateAccount: Routes
    @Serializable object ForgotPassword: Routes

    @Serializable object Authorized: Routes

    @Serializable class Note(val noteId: Int): Routes
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
            popUpTo<BaseHomeRoute> {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}