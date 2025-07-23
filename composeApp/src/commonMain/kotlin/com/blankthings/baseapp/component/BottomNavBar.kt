package com.blankthings.baseapp.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination.Companion.hasRoute
import com.blankthings.baseapp.navigation.TopDestinations
import com.blankthings.baseapp.navigation.AppState
import com.blankthings.baseapp.ui.account.navigateToAccount
import com.blankthings.baseapp.ui.home.navigateToHome
import com.blankthings.baseapp.ui.settings.navigateToSettings

@Composable
fun BottomNavBar(appState: AppState) {
    NavigationBar {
        TopDestinations.entries.forEach { topDestination ->
            val isSelected = appState.currentDestination?.hasRoute(topDestination.route) == true
            NavigationBarItem(
                icon = { Icon( imageVector = topDestination.icon, contentDescription = topDestination.contentDescription) },
                selected = isSelected,
                onClick = {
                    when (topDestination) {
                        TopDestinations.HOME -> appState.navController.navigateToHome()
                        TopDestinations.ACCOUNT -> appState.navController.navigateToAccount()
                        TopDestinations.SETTINGS -> appState.navController.navigateToSettings()
                    }
                }
            )
        }
    }
}