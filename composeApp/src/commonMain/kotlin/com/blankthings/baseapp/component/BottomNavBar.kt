package com.blankthings.baseapp.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.blankthings.baseapp.navigation.NavActions
import com.blankthings.baseapp.navigation.TopDestinations
import com.blankthings.baseapp.ui.account.navigateToAccount
import com.blankthings.baseapp.ui.home.navigateToHome
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavBar(currentRoute: String, navActions: NavActions) {
    NavigationBar {
        TopDestinations.entries.forEach { topDestination ->
            // TODO - Fix this. Shouldn't be a string comparison.
            val isSelected = currentRoute.contains(topDestination.route.toString())
            NavigationBarItem(
                icon = { Icon( imageVector = topDestination.icon, contentDescription = topDestination.contentDescription) },
                selected = isSelected,
                onClick = {
                    when (topDestination) {
                        TopDestinations.HOME -> navActions.navHostController::navigateToHome
                        TopDestinations.ACCOUNT -> navActions.navHostController::navigateToAccount
                        TopDestinations.SETTINGS -> navActions.navigateToSettings()
                    }
                }
            )
        }
    }
}