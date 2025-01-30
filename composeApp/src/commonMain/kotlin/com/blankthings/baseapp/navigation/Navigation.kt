package com.blankthings.baseapp.navigation

import AccountScreen
import CreateAccountScreen
import ForgotPasswordScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.blankthings.baseapp.ui.LoginRoute
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.LoginViewModel
import com.blankthings.baseapp.ui.SettingsScreen

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
            val viewModel: LoginViewModel = viewModel(factory = LoginViewModel.provideFactory())
            LoginRoute(
                viewModel,
                { navHostController.navigate(Routes.CreateAccount) },
                { navHostController.navigate(Routes.ForgotPassword) }
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