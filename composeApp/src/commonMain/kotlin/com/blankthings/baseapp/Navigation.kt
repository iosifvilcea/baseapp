package com.blankthings.baseapp

import AccountScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.settings
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.SettingsScreen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

enum class Screen(val title: StringResource, val icon: ImageVector) {
    Home(Res.string.home, Icons.Default.Home),
    Account(Res.string.account, Icons.Default.Person),
    Settings(Res.string.settings, Icons.Default.Settings)
}

@Composable
fun BottomNavBar(navHostController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            selected = true,
            label = { Text(text = stringResource(Screen.Home.title)) },
            onClick = { navHostController.navigate(Screen.Home.name) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = null) },
            selected = false,
            label = { Text(text = stringResource(Screen.Account.title)) },
            onClick = { navHostController.navigate(Screen.Account.name) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = null) },
            selected = false,
            label = { Text(text = stringResource(Screen.Settings.title)) },
            onClick = { navHostController.navigate(Screen.Settings.name) }
        )
    }
}

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screen.Home.name) {
            HomeScreen(navHostController)
        }
        composable(route = Screen.Account.name) {
            AccountScreen(navHostController)
        }
        composable(route = Screen.Settings.name) {
            SettingsScreen(navHostController)
        }
    }
}