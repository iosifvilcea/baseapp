package com.blankthings.baseapp.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface AuthUiState {
    data object Default: AuthUiState
    data object Loading: AuthUiState
    data object LoginSuccess: AuthUiState
}

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Default)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun reset() {
        _uiState.value = AuthUiState.Default
    }

    fun login(username: String, password: String) {
        _uiState.value = AuthUiState.LoginSuccess
    }
}