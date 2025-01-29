package com.blankthings.baseapp.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<LoadingState>(LoadingState.Empty)
    val uiState: StateFlow<LoadingState> = _uiState.asStateFlow()

    fun reset() {
        _uiState.value = LoadingState.Empty
    }

    fun login(username: String, password: String) {
        _uiState.value = LoadingState.Loading

        // TODO: Some work

        _uiState.value = LoadingState.Success
    }
}

// TODO: Move Strings
//  enforce message through @StringRes?
enum class SignInError(val message: String) {
    INVALID_EMAIL("error_invalid_email_address"),
    INVALID_PASSWORD("error_invalid_password_length"),
    SERVER("error_server_failed"),
}

sealed interface LoadingState {
    data object Empty: LoadingState
    data object Loading: LoadingState
    data object Success: LoadingState
    data class Failure(val errors: MutableSet<SignInError>, val message: String?) : LoadingState
}