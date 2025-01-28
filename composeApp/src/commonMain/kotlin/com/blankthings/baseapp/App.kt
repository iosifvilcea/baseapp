package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navHostController: NavHostController = rememberNavController()

    val currentRoute = navHostController
        .currentBackStackEntryFlow
        .collectAsState(initial = navHostController.currentBackStackEntry)
        .value?.destination?.route

    MaterialTheme {
        Scaffold(
            bottomBar = {
                if (Routes.shouldShowBottomBar(currentRoute)) {
                    bottomNav(navController = navHostController)
                }
            }
        ) {
            NavigationHost(navHostController = navHostController)
        }
    }
}

@Composable
fun bottomNav(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
            selected = true,
            onClick = { navController.navigate(Routes.Home) }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            selected = false,
            onClick = { navController.navigate(Routes.Account) }
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "") },
            selected = false,
            onClick = { navController.navigate(Routes.Settings) }
        )
    }
}