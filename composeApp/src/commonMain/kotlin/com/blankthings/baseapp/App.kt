package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.app_name
import baseapp.composeapp.generated.resources.error_no_internet_connection
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.settings
import com.blankthings.baseapp.analytics.Analytics
import com.blankthings.baseapp.analytics.AnalyticsEvent
import com.blankthings.baseapp.component.BottomNavBar
import com.blankthings.baseapp.component.TopAppBar
import com.blankthings.baseapp.data.AuthRepositoryImpl
import com.blankthings.baseapp.data.NoteRepositoryImpl
import com.blankthings.baseapp.data.UserDataRepositoryImpl
import com.blankthings.baseapp.navigation.NavigationHost
import com.blankthings.baseapp.navigation.Routes
import com.blankthings.baseapp.navigation.TopDestinations
import com.blankthings.baseapp.ui.rememberAppState
import com.blankthings.baseapp.utils.Constants
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Analytics.track(AnalyticsEvent.APPLICATION_OPENED)

    val httpClient = AppDependencies.getHttpClient()
    val networkMonitor = AppDependencies.getNetworkMonitor()
    val dataStoreManager = AppDependencies.getDataStoreManager()
    val userDataRepository = UserDataRepositoryImpl(dataStoreManager)
    val noteRepository = NoteRepositoryImpl()
    val authRepository = AuthRepositoryImpl(httpClient)

    val appState = rememberAppState(networkMonitor = networkMonitor)

    val currentRoute = appState.navController
        .currentBackStackEntryFlow
        .collectAsState(initial = appState.navController.currentBackStackEntry)
        .value?.destination?.route ?: Routes.Login.toString()

    val snackbarHostState = remember { SnackbarHostState() }

    MaterialTheme {
        Scaffold(
            topBar = {
                AnimatedVisibility(
                    visible = showNavBars(currentRoute),
                    enter = slideInVertically(initialOffsetY = { -it }),
                    exit = slideOutVertically(targetOffsetY = { -it }),
                    content = { TopAppBar(title = stringResource(Res.string.app_name)) }
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                AnimatedVisibility(
                    visible = showNavBars(currentRoute),
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                    content = { BottomNavBar(currentRoute, appState.navActions) }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationHost(
                    authRepository = authRepository,
                    userDataRepository = userDataRepository,
                    noteRepository = noteRepository,
                    navActions = appState.navActions,
                    snackbarHostState = snackbarHostState
                )
                val isOffline by appState.isOffline.collectAsStateWithLifecycle()
                val notConnectedMessage = stringResource(Res.string.error_no_internet_connection)
                LaunchedEffect(isOffline) {
                    if (isOffline) {
                        snackbarHostState.showSnackbar(
                            message = notConnectedMessage,
                            duration = Indefinite,
                        )
                    }
                }
            }
            printBackStack(navController = appState.navController)
        }
    }
}

@Composable
fun printBackStack(navController: NavController) {
    val backStackEntries by navController.currentBackStack.collectAsState()
    LaunchedEffect(backStackEntries) {
        val stack = backStackEntries.map { it.destination.route }
        Analytics.track(AnalyticsEvent.APPLICATION_BACKSTACK, mutableMapOf("" to stack))
    }
}

@Composable
fun showNavBars(route: String): Boolean =
    when (route.substringAfterLast(Constants.DELIMITER_DOT)) {
        stringResource(Res.string.home) -> true
        stringResource(Res.string.account) -> true
        stringResource(Res.string.settings) -> true
        else -> false
    }