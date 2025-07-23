package com.blankthings.baseapp.ui.settings

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.navigation.Routes
import com.blankthings.baseapp.ui.home.AuthorizedRoute
import kotlinx.serialization.Serializable

@Serializable object SettingsRoute

fun NavController.navigateToSettings() {
    navigate(SettingsRoute) {
        popUpTo<AuthorizedRoute> {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.settingsScreen(
    userDataRepository: UserDataRepository
) {
    composable<SettingsRoute> {
        val viewModel: SettingsViewModel = viewModel(
            factory = SettingsViewModel.provideFactory(userDataRepository)
        )
        SettingsScreen(viewModel)
    }
}