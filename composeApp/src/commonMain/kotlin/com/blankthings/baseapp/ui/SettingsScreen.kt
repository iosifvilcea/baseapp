package com.blankthings.baseapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    navController: NavHostController = rememberNavController()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Settings Screen",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen()
    }
}