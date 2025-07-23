package com.blankthings.baseapp.ui.splash

import SplashScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable object SplashRoute

fun NavGraphBuilder.splashScreen(onLoadingDone: () -> Unit) {
    composable<SplashRoute> {
        SplashScreen(onLoadingDone)
    }
}