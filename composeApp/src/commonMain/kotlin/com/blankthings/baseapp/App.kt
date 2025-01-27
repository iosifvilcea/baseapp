package com.blankthings.baseapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()
    MaterialTheme {
        Scaffold(
            bottomBar = { BottomNavBar(navController) }
        ) {
            NavigationHost(navHostController = navController)
        }
    }
}