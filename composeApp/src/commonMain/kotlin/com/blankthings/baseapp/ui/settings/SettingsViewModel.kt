package com.blankthings.baseapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.blankthings.baseapp.data.UserDataRepository
import com.blankthings.baseapp.model.DarkThemeConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.reflect.KClass
import kotlin.time.Duration.Companion.seconds

class SettingsViewModel(val userDataRepository: UserDataRepository): ViewModel() {
    val settingsUiState: StateFlow<SettingsUiState> =
        userDataRepository.userData.map { userData ->
            val isDarkMode = userData.darkThemeConfig == DarkThemeConfig.DARK
            SettingsUiState.Success(
                settings = UserSettings(isDarkMode = isDarkMode)
            )
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5.seconds.inWholeMilliseconds),
            initialValue = SettingsUiState.Loading
        )

    fun updateDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }

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