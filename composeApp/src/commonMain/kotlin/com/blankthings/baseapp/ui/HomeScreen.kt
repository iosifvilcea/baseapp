package com.blankthings.baseapp.ui

import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.account
import baseapp.composeapp.generated.resources.compose_multiplatform
import baseapp.composeapp.generated.resources.home
import baseapp.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.StringResource

enum class Screens(val title: StringResource) {
    Home(title = Res.string.home),
    Account(title = Res.string.account),
    Settings(title = Res.string.settings),
}