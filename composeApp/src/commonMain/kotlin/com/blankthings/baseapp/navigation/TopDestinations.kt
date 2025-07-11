package com.blankthings.baseapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopDestinations(
    val icon: ImageVector,
    val contentDescription: String,
    val route: Routes,
    val baseRoute: Routes
) {
    HOME(
        icon = Icons.Default.Home,
        contentDescription = "Home",
        route = Routes.Home,
        baseRoute = Routes.Authorized
    ),
    ACCOUNT(
        icon = Icons.Default.Person,
        contentDescription = "Account",
        route = Routes.Account,
        baseRoute = Routes.Authorized
    ),
    SETTINGS(
        icon = Icons.Default.Settings,
        contentDescription = "Settings",
        route = Routes.Settings,
        baseRoute = Routes.Authorized
    )
}