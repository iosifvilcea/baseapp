package com.blankthings.baseapp.navigation

import CreateAccountScreen
import ForgotPasswordScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.blankthings.baseapp.data.AuthRepository
import com.blankthings.baseapp.data.NoteRepository
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.ui.NoteScreen
import com.blankthings.baseapp.ui.account.accountScreen
import com.blankthings.baseapp.ui.home.HomeRoute
import com.blankthings.baseapp.ui.home.homeScreen
import com.blankthings.baseapp.ui.login.LoginRoute
import com.blankthings.baseapp.ui.login.LoginViewModel
import com.blankthings.baseapp.ui.settings.settingsScreen

@Composable
fun NavigationHost(
    authRepository: AuthRepository,
    userDataRepository: UserDataRepository,
    noteRepository: NoteRepository,
    navActions: NavActions,
    snackbarHostState: SnackbarHostState
) {
    val navController = navActions.navHostController
    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Routes.Splash> {
            SplashScreen {
                navActions.navigateToLogin.invoke()
            }
        }
        composable<Routes.Login> {
            val viewModel: LoginViewModel = viewModel(
                factory = LoginViewModel.provideFactory(authRepository)
            )
            LoginRoute(
                loginViewModel = viewModel,
                navActions = navActions,
                snackbarHostState = snackbarHostState
            )
        }
        composable<Routes.CreateAccount> {
            CreateAccountScreen()
        }
        composable<Routes.ForgotPassword> {
            ForgotPasswordScreen {
                navActions.navigateToLogin.invoke()
            }
        }
        navigation<Routes.Authorized>(startDestination = HomeRoute) {
            homeScreen(noteRepository) {
                navActions.navigateToNote.invoke(it)
            }
            composable<Routes.Note> {
                val noteId = it.toRoute<Routes.Note>().noteId
                val note = noteRepository.getNote(noteId)
                NoteScreen(note.title, note.content) {
                    navActions.navHostController.popBackStack()
                }
            }
            accountScreen {
                navActions.navigateToLogin.invoke()
            }
            settingsScreen(userDataRepository)
        }
    }
}