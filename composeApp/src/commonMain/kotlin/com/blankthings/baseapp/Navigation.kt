package com.blankthings.baseapp

import AccountScreen
import CreateAccountScreen
import ForgotPasswordScreen
import LoginScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.LoginViewModel
import com.blankthings.baseapp.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable data object Splash: Routes
    @Serializable data object Login: Routes
    @Serializable data object CreateAccount: Routes
    @Serializable data object ForgotPassword: Routes

    @Serializable data object Authorized: Routes
    @Serializable data object Home: Routes
    @Serializable data object Account: Routes
    @Serializable data object Settings: Routes
}

@Composable
fun NavigationHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Splash,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Routes.Splash> {
            SplashScreen { navHostController.navigate(Routes.Login) }
        }

        composable<Routes.Login> {
            LoginScreen(
                onLoginClick = { navHostController.navigate(Routes.Authorized) },
                onCreateAccountClick = { navHostController.navigate(Routes.CreateAccount) },
                onForgotPasswordClick = { navHostController.navigate(Routes.ForgotPassword) }
            )
        }
        composable<Routes.CreateAccount> {
            CreateAccountScreen()
        }
        composable<Routes.ForgotPassword> {
            ForgotPasswordScreen {
                navHostController.popBackStack(route = Routes.Login, inclusive = false)
            }
        }
        navigation<Routes.Authorized>(startDestination = Routes.Home) {
            composable<Routes.Home> {
                HomeScreen(onNavigateToRoute = navHostController::navigate)
            }
            composable<Routes.Account> {
                AccountScreen {
                    navHostController.popBackStack(route = Routes.Login, inclusive = false)
                }
            }
            composable<Routes.Settings> {
                SettingsScreen()
            }
        }
    }
}