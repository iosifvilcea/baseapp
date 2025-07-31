package com.blankthings.baseapp.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blankthings.baseapp.model.DarkThemeConfig
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    settingsUiState: SettingsUiState,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Settings Screen",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
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

@Preview
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen(SettingsUiState.Success(UserSettings(isDarkMode = false))) {}
    }
}