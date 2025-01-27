package com.blankthings.baseapp.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import com.blankthings.baseapp.Routes

@Composable
fun AppScreen(
    onNavigateToRoute: (Routes) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
                    selected = true,
                    onClick = { onNavigateToRoute(Routes.Home) }
                )
                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                    selected = false,
                    onClick = { onNavigateToRoute(Routes.Account) }
                )
                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "") },
                    selected = false,
                    onClick = { onNavigateToRoute(Routes.Settings) }
                )
            }
        }
    ) {}
}

