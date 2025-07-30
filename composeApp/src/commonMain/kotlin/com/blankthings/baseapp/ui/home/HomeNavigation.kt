package com.blankthings.baseapp.ui.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.blankthings.baseapp.data.NoteRepository
import kotlinx.serialization.Serializable


@Serializable object AuthorizedRoute
@Serializable object BaseHomeRoute
@Serializable object HomeRoute

fun NavController.navigateToHome() {
    navigate(HomeRoute) {
        popUpTo<HomeRoute> {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavGraphBuilder.homeScreen(
    noteRepository: NoteRepository,
    onNoteClicked: (Long) -> Unit
) {
    composable<HomeRoute> {
        val viewModel: HomeViewModel = viewModel(
            factory = HomeViewModel.provideFactory(noteRepository)
        )
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(uiState) { noteId ->
            onNoteClicked.invoke(noteId)
        }
    }
}