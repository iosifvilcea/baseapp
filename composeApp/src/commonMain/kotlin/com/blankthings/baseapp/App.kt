package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.settings
import com.blankthings.baseapp.utils.Constants
import org.jetbrains.compose.resources.stringResource
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
                AnimatedVisibility(
                    visible = shouldShowBottomBar(currentRoute),
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                    content = {
                        bottomNav(navController = navHostController)
                    }
                )
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

@Composable
fun shouldShowBottomBar(route: String?): Boolean {
    route?.let {
        return when (route.substringAfterLast(Constants.DELIMITER_DOT)) {
            stringResource(Res.string.home) -> true
            stringResource(Res.string.account) -> true
            stringResource(Res.string.settings) -> true
            else -> false
        }
    }.apply {
        return false
    }
}