package com.blankthings.baseapp.ui.note

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.ui.home.BaseHomeRoute
import com.blankthings.baseapp.ui.settings.SettingsScreen
import com.blankthings.baseapp.ui.settings.SettingsViewModel
import kotlinx.serialization.Serializable

@Serializable
class NoteRoute(val noteId: Int)

fun NavController.navigateToNote(noteId: Int) {
    navigate(NoteRoute(noteId)) {
        popUpTo<BaseHomeRoute> {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.noteScreen(
    userDataRepository: UserDataRepository
) {
    composable<NoteRoute> {
        val viewModel: SettingsViewModel = viewModel(
            factory = SettingsViewModel.Companion.provideFactory(userDataRepository)
        )
        SettingsScreen(viewModel)
    }
}