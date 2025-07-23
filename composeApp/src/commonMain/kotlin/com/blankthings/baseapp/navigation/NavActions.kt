package com.blankthings.baseapp.navigation

import androidx.navigation.NavHostController
import com.blankthings.baseapp.model.Note
import com.blankthings.baseapp.ui.home.AuthorizedRoute
import com.blankthings.baseapp.ui.home.BaseHomeRoute
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable object Splash: Routes
    @Serializable class Note(val noteId: Int): Routes
}

class NavActions(val navHostController: NavHostController) {

    val navigateToAuthorized: () -> Unit = {
        navHostController.navigate(AuthorizedRoute)
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