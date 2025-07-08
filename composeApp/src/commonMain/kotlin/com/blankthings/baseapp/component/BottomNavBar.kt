package com.blankthings.baseapp.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import com.blankthings.baseapp.navigation.NavActions

@Composable
fun BottomNavBar(navActions: NavActions) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
            selected = true,
            onClick = navActions.navigateToHome
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            selected = false,
            onClick = navActions.navigateToAccount
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "") },
            selected = false,
            onClick = navActions.navigateToSettings
        )
    }
}