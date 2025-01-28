package com.blankthings.baseapp

import AccountScreen
import CreateAccountScreen
import ForgotPasswordScreen
import LoginScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    companion object {
        fun shouldShowBottomBar(route: String?): Boolean {
            // TODO: Ugly. Fix. 
            return if (route?.contains(Home.toString()) == true) true
            else if (route?.contains(Account.toString()) == true) true
            else if (route?.contains(Settings.toString()) == true) true
            else false
        }
    }

    @Serializable data object Splash: Routes
    @Serializable data object OnBoarding: Routes
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
            ForgotPasswordScreen()
        }

        navigation<Routes.Authorized>(startDestination = Routes.Home) {
            composable<Routes.Home> {
                HomeScreen(onNavigateToRoute = navHostController::navigate)
            }
            composable<Routes.Account> {
                AccountScreen()
            }
            composable<Routes.Settings> {
                SettingsScreen()
            }
        }
    }
}