package com.blankthings.baseapp.navigation

import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.blankthings.baseapp.data.AuthRepository
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.ui.AppState
import com.blankthings.baseapp.ui.NoteScreen
import com.blankthings.baseapp.ui.account.accountScreen
import com.blankthings.baseapp.ui.home.AuthorizedRoute
import com.blankthings.baseapp.ui.home.HomeRoute
import com.blankthings.baseapp.ui.home.homeScreen
import com.blankthings.baseapp.ui.login.loginScreen
import com.blankthings.baseapp.ui.login.navigateToLogin
import com.blankthings.baseapp.ui.settings.settingsScreen
import com.blankthings.baseapp.utils.ErrorType

@Composable
fun NavigationHost(
    authRepository: AuthRepository,
    userDataRepository: UserDataRepository,
    noteRepository: NoteRepository,
    appState: AppState,
    onShowSnackBar: (ErrorType, String) -> Unit
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Routes.Splash> {
            SplashScreen { appState.navController.navigateToLogin() }
        }
        loginScreen(authRepository, appState, onShowSnackBar)
        navigation<AuthorizedRoute>(startDestination = HomeRoute) {
            homeScreen(noteRepository) {
                // TODO - NoteNavigation
//                appState.navActions.navigateToNote.invoke(it)
            }
            composable<Routes.Note> {
                val noteId = it.toRoute<Routes.Note>().noteId
                val note = noteRepository.getNote(noteId)
                NoteScreen(note.title, note.content) {
                    appState.navController.popBackStack()
                }
            }
            accountScreen { appState.navController.navigateToLogin() }
            settingsScreen(userDataRepository)
        }
    }
}