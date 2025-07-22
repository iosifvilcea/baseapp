package com.blankthings.baseapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blankthings.baseapp.navigation.NavActions
import com.blankthings.baseapp.navigation.Routes
import com.blankthings.baseapp.utils.NetworkMonitor

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
    networkMonitor: NetworkMonitor
): AppState {
    val navAction = remember(navHostController) {
        NavActions(navHostController)
    }
    return remember(navHostController) {
        AppState(
            navController = navHostController,
            navActions = navAction,
            networkMonitor = networkMonitor
        )
    }
}

class AppState(
    val navController: NavHostController,
    val navActions: NavActions,
    networkMonitor: NetworkMonitor
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            // Collect the currentBackStackEntryFlow as a state
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            // Fallback to previousDestination if currentEntry is null
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }
}