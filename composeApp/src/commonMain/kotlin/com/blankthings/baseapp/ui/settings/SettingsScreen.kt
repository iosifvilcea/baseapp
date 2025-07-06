package com.blankthings.baseapp.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val settingsUiState: SettingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsScreen(settingsUiState)
}

@Composable
fun SettingsScreen(settingsUiState: SettingsUiState) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Settings Screen",
            textAlign = TextAlign.Center
        )
    }
}