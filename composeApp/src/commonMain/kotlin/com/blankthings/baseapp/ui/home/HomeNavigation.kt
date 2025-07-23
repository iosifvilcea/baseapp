package com.blankthings.baseapp.ui.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.navigation.Routes
import kotlinx.serialization.Serializable

@Serializable data object HomeRoute

fun NavController.navigateToHome() {
    navigate(HomeRoute) {
        popUpTo<Routes.Authorized> {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.homeScreen(
    noteRepository: NoteRepository,
    onNoteClicked: (Int) -> Unit
) {
    composable<HomeRoute> {
        val viewModel: HomeViewModel = viewModel(
            factory = HomeViewModel.provideFactory(noteRepository)
        )
        HomeScreen(viewModel.getNotes()) { noteId ->
            onNoteClicked.invoke(noteId)
        }
    }
}