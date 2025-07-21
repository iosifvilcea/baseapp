package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.app_name
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.settings
import com.blankthings.baseapp.analytics.Analytics
import com.blankthings.baseapp.analytics.AnalyticsEvent
import com.blankthings.baseapp.component.BottomNavBar
import com.blankthings.baseapp.component.NoConnectionBar
import com.blankthings.baseapp.component.TopAppBar
import com.blankthings.baseapp.data.AuthRepositoryImpl
import com.blankthings.baseapp.data.NoteRepositoryImpl
import com.blankthings.baseapp.data.UserDataRepositoryImpl
import com.blankthings.baseapp.navigation.NavActions
import com.blankthings.baseapp.navigation.NavigationHost
import com.blankthings.baseapp.navigation.Routes
import com.blankthings.baseapp.navigation.TopDestinations
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

    val navHostController: NavHostController = rememberNavController()
    val navAction = remember(navHostController) {
        NavActions(navHostController)
    }
    val authRepository = AuthRepositoryImpl(httpClient)

    val currentRoute = navHostController
        .currentBackStackEntryFlow
        .collectAsState(initial = navHostController.currentBackStackEntry)
        .value?.destination?.route ?: Routes.Login.toString()

    val snackbarHostState = remember { SnackbarHostState() }

    val topDestinations: List<TopDestinations> = TopDestinations.entries

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
                    content = { BottomNavBar(currentRoute, topDestinations, navAction) }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                networkMonitor.isOnline.collectAsState(initial = true).value.let { isOnline ->
                    if (!isOnline) {
                        NoConnectionBar()
                    }
                }
                NavigationHost(
                    authRepository = authRepository,
                    userDataRepository = userDataRepository,
                    noteRepository = noteRepository,
                    navActions = navAction,
                    snackbarHostState = snackbarHostState
                )
            }
            printBackStack(navController = navHostController)
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