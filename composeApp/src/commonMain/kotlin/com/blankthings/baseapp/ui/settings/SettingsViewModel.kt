package com.blankthings.baseapp.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel: ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> = MutableStateFlow(SettingsUiState.Loading)
}

data class UserSettings(val isLightMode: Boolean)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val settings: UserSettings) : SettingsUiState
}
