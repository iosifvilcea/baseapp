package com.blankthings.baseapp

import AccountScreen
import CreateAccountScreen
import ForgotPasswordScreen
import LoginScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.SettingsScreen
import kotlinx.serialization.Serializable

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
            SplashScreen()
        }
        composable<Routes.Login> {
            LoginScreen { navHostController.navigate(Routes.Home) }
        }
        composable<Routes.Home> {
            HomeScreen()
        }
        composable<Routes.CreateAccount> {
            CreateAccountScreen()
        }
        composable<Routes.ForgotPassword> {
            ForgotPasswordScreen()
        }
        composable<Routes.Account> {
            AccountScreen()
        }
        composable<Routes.Settings> {
            SettingsScreen()
        }
    }
}