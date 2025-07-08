package com.blankthings.baseapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.blankthings.baseapp.data.UserDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KClass

class SettingsViewModel(userDataRepository: UserDataRepository): ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> = MutableStateFlow(SettingsUiState.Loading)

    companion object {
        fun provideFactory(
            userDataRepository: UserDataRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return SettingsViewModel(userDataRepository) as T
            }
        }
    }
}

data class UserSettings(val isDarkMode: Boolean)

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val settings: UserSettings) : SettingsUiState
}