package com.blankthings.baseapp

import AccountScreen
import LoginScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.create_account
import baseapp.composeapp.generated.resources.forgot_password
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.login
import baseapp.composeapp.generated.resources.onboarding
import baseapp.composeapp.generated.resources.settings
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.SettingsScreen
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Serializable
sealed interface Routes {
    @Serializable data object Splash: Routes
    @Serializable data object Home: Routes
    @Serializable data object OnBoarding: Routes
    @Serializable data object Login: Routes
    @Serializable data object CreateAccount: Routes
    @Serializable data object ForgotPassword: Routes
    @Serializable data object Account: Routes
    @Serializable data object Settings: Routes
}

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Login,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Routes.Splash> {
            SplashScreen(navHostController)
        }
        composable<Routes.Login> {
            LoginScreen(navHostController)
        }
        composable<Routes.Home> {
            HomeScreen(navHostController)
        }
        composable<Routes.CreateAccount> {
            CreateAccount(navHostController)
        }
        composable<Routes.ForgotPassword> {
            ForgotPasswordScreen(navHostController)
        }
        composable<Routes.Account> {
            AccountScreen(navHostController)
        }
        composable<Routes.Settings> {
            SettingsScreen(navHostController)
        }
    }
}