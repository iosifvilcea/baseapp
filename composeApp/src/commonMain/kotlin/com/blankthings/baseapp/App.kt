package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.settings
import com.blankthings.baseapp.analytics.Analytics
import com.blankthings.baseapp.analytics.AnalyticsEvent
import com.blankthings.baseapp.data.AuthRepositoryImpl
import com.blankthings.baseapp.data.KtorClient
import com.blankthings.baseapp.datastore.DataStoreFactory
import com.blankthings.baseapp.datastore.DataStoreManagerImpl
import com.blankthings.baseapp.navigation.NavActions
import com.blankthings.baseapp.navigation.NavigationHost
import com.blankthings.baseapp.navigation.Routes
import com.blankthings.baseapp.utils.Constants
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Analytics.track(AnalyticsEvent.APPLICATION_OPENED)

    // TODO - Implement
    val dataStoreManager = DataStoreManagerImpl(DataStoreFactory())

    val httpClient = KtorClient.create()
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

    MaterialTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            bottomBar = {
                AnimatedVisibility(
                    visible = shouldShowBottomBar(currentRoute),
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                    content = { bottomNav(navAction) }
                )
            }
        ) {
            NavigationHost(
                authRepository = authRepository,
                navActions = navAction,
                snackbarHostState = snackbarHostState
            )
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
fun bottomNav(navActions: NavActions) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
            selected = true,
            onClick = navActions.navigateToHome
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            selected = false,
            onClick = navActions.navigateToAccount
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "") },
            selected = false,
            onClick = navActions.navigateToSettings
        )
    }
}

@Composable
fun shouldShowBottomBar(route: String): Boolean =
    when (route.substringAfterLast(Constants.DELIMITER_DOT)) {
        stringResource(Res.string.home) -> true
        stringResource(Res.string.account) -> true
        stringResource(Res.string.settings) -> true
        else -> false
    }