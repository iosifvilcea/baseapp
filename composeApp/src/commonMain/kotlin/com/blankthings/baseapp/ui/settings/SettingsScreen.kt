package com.blankthings.baseapp.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.blankthings.baseapp.model.DarkThemeConfig

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val settingsUiState: SettingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsScreen(settingsUiState, viewModel::updateDarkThemeConfig)
}

@Composable
fun SettingsScreen(
    settingsUiState: SettingsUiState,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Settings Screen",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            when (settingsUiState) {
                SettingsUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        strokeWidth = 4.dp
                    )
                }
                is SettingsUiState.Success -> {
                    Text(text = "Toggle DarkMode")
                    Switch(
                        checked = settingsUiState.settings.isDarkMode,
                        onCheckedChange = { isDarkMode ->
                            // TODO - Move this check to the ViewModel
                            val darkModeConfig = if (isDarkMode) DarkThemeConfig.DARK else DarkThemeConfig.LIGHT
                            onChangeDarkThemeConfig.invoke(darkModeConfig)
                        }
                    )
                }
            }
        }
    }
}