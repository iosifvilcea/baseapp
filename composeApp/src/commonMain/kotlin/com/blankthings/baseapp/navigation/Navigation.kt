package com.blankthings.baseapp.navigation

import AccountScreen
import CreateAccountScreen
import ForgotPasswordScreen
import SplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.blankthings.baseapp.data.AuthRepositoryImpl
import com.blankthings.baseapp.data.KtorClient
import com.blankthings.baseapp.ui.HomeScreen
import com.blankthings.baseapp.ui.login.LoginRoute
import com.blankthings.baseapp.ui.login.LoginViewModel
import com.blankthings.baseapp.ui.SettingsScreen
import io.ktor.client.HttpClient

@Composable
fun NavigationHost(
    httpClient: HttpClient,
    navActions: NavActions,
    snackbarHostState: SnackbarHostState
) {
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
            val viewModel: LoginViewModel = viewModel(
                factory = LoginViewModel.provideFactory(
                    AuthRepositoryImpl(httpClient)
                )
            )
            LoginRoute(
                loginViewModel = viewModel,
                navActions = navActions,
                snackbarHostState = snackbarHostState
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