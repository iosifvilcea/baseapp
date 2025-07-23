package com.blankthings.baseapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.blankthings.baseapp.data.AuthRepository
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.ui.account.accountScreen
import com.blankthings.baseapp.ui.home.AuthorizedRoute
import com.blankthings.baseapp.ui.home.HomeRoute
import com.blankthings.baseapp.ui.home.homeScreen
import com.blankthings.baseapp.ui.login.loginScreen
import com.blankthings.baseapp.ui.login.navigateToLogin
import com.blankthings.baseapp.ui.note.navigateToNote
import com.blankthings.baseapp.ui.note.noteScreen
import com.blankthings.baseapp.ui.settings.settingsScreen
import com.blankthings.baseapp.ui.splash.SplashRoute
import com.blankthings.baseapp.ui.splash.splashScreen
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
        startDestination = SplashRoute,
        modifier = Modifier.fillMaxSize()
    ) {
        splashScreen(navController::navigateToLogin)
        loginScreen(authRepository, appState, onShowSnackBar)
        navigation<AuthorizedRoute>(startDestination = HomeRoute) {
            homeScreen(noteRepository) { navController.navigateToNote(it) }
            noteScreen(noteRepository, onBackClicked = navController::popBackStack)
            accountScreen(navController::navigateToLogin)
            settingsScreen(userDataRepository)
        }
    }
}