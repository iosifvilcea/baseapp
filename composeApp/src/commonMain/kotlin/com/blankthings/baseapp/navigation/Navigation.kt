package com.blankthings.baseapp.navigation

import AccountScreen
import CreateAccountScreen
import ForgotPasswordScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.login.LoginRoute
import com.blankthings.baseapp.ui.login.LoginViewModel
import com.blankthings.baseapp.ui.SettingsScreen

@Composable
fun NavigationHost(navActions: NavActions) {
    NavHost(
        navController = navActions.navHostController,
        startDestination = Routes.Splash,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<Routes.Splash> {
            SplashScreen {
                navActions.navigateToLogin.invoke()
            }
        }
        composable<Routes.Login> {
            val viewModel: LoginViewModel = viewModel()
            LoginRoute(
                viewModel,
                navActions = navActions
            )
        }
        composable<Routes.CreateAccount> {
            CreateAccountScreen()
        }
        composable<Routes.ForgotPassword> {
            ForgotPasswordScreen {
                navActions.navigateToLogin.invoke()
            }
        }
        navigation<Routes.Authorized>(startDestination = Routes.Home) {
            composable<Routes.Home> {
                HomeScreen()
            }
            composable<Routes.Account> {
                AccountScreen {
                    navActions.navigateToLogin.invoke()
                }
            }
            composable<Routes.Settings> {
                SettingsScreen()
            }
        }
    }
}