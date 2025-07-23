package com.blankthings.baseapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.blankthings.baseapp.ui.account.AccountRoute
import com.blankthings.baseapp.ui.home.BaseHomeRoute
import com.blankthings.baseapp.ui.home.HomeRoute
import com.blankthings.baseapp.ui.settings.SettingsRoute
import kotlin.reflect.KClass

enum class TopDestinations(
    val icon: ImageVector,
    val contentDescription: String,
    val route: KClass<*>,
    val baseRoute: KClass<*>
) {
    HOME(
        icon = Icons.Default.Home,
        contentDescription = "Home",
        route = HomeRoute::class,
        baseRoute = BaseHomeRoute::class
    ),
    ACCOUNT(
        icon = Icons.Default.Person,
        contentDescription = "Account",
        route = AccountRoute::class,
        baseRoute = AccountRoute::class
    ),
    SETTINGS(
        icon = Icons.Default.Settings,
        contentDescription = "Settings",
        route = SettingsRoute::class,
        baseRoute = SettingsRoute::class
    )
}