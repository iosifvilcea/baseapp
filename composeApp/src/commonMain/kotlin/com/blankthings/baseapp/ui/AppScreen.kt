package com.blankthings.baseapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blankthings.baseapp.NavigationHost
import com.blankthings.baseapp.Routes
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppScreen(
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
                    selected = true,
                    onClick = { navHostController.navigate(Routes.Home) }
                )
                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
                    selected = false,
                    onClick = { navHostController.navigate(Routes.Account) }
                )
                BottomNavigationItem(
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "") },
                    selected = false,
                    onClick = { navHostController.navigate(Routes.Settings) }
                )
            }
        }
    ) {}
}

