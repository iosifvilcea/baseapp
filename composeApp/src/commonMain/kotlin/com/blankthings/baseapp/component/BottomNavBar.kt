package com.blankthings.baseapp.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.blankthings.baseapp.navigation.NavActions
import com.blankthings.baseapp.navigation.TopDestinations

@Composable
fun BottomNavBar(currentRoute: String, topDestinations: List<TopDestinations>, navActions: NavActions) {
    NavigationBar {
        topDestinations.forEach { topDestination ->
            // TODO - Fix this. Shouldn't be a string comparison.
            val isSelected = currentRoute.contains(topDestination.route.toString())
            NavigationBarItem(
                icon = { Icon( imageVector = topDestination.icon, contentDescription = topDestination.contentDescription) },
                selected = isSelected,
                onClick = {
                    when (topDestination) {
                        TopDestinations.HOME -> navActions.navigateToHome()
                        TopDestinations.ACCOUNT -> navActions.navigateToAccount()
                        TopDestinations.SETTINGS -> navActions.navigateToSettings()
                    }
                }
            )
        }
    }
}