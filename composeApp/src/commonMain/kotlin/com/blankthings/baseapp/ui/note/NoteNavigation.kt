package com.blankthings.baseapp.ui.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.ui.home.BaseHomeRoute
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
    noteRepository: NoteRepository,
    onBackClicked: () -> Unit
) {
    composable<NoteRoute> {
        val noteId = it.toRoute<NoteRoute>().noteId
        val note = noteRepository.getNote(noteId)
        NoteScreen(note.title, note.content, onBackClicked)
    }
}