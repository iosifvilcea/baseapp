package com.blankthings.baseapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankthings.baseapp.data.AuthManager
import com.blankthings.baseapp.data.LoginResult
import com.blankthings.baseapp.utils.ErrorMessage
import com.blankthings.baseapp.utils.ErrorType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AuthUiState {
    data object Default: AuthUiState
    data object Loading: AuthUiState
    data object Success: AuthUiState
    data class Failure(val errorType: ErrorType, val errorMessage: String): AuthUiState
}

class LoginViewModel(
    private val authManager: AuthManager,
): ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Default)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun reset() {
        _uiState.value = AuthUiState.Default
    }

    fun login(emailString: String, passwordString: String) {
        if (emailString.isNullOrEmpty() || passwordString.isNullOrEmpty()) {
            return
        }

        _uiState.value = AuthUiState.Loading

        viewModelScope.launch {
            val result = authManager.loginWithEmailAndPassword(
                email = emailString,
                password = passwordString,
            )
            when (result) {
                is LoginResult.Success -> _uiState.value = AuthUiState.Success
                is LoginResult.Failed -> _uiState.value = AuthUiState.Failure(ErrorType.SERVER, result.message)
            }
        }
    }
}