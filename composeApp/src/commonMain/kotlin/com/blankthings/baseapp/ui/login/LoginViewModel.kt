package com.blankthings.baseapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.blankthings.baseapp.data.AuthRepository
import com.blankthings.baseapp.data.LoginResult
import com.blankthings.baseapp.utils.ErrorType
import com.blankthings.baseapp.utils.Patterns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

sealed interface AuthUiState {
    data object Default: AuthUiState
    data object Loading: AuthUiState
    data object Success: AuthUiState
    data class Failure(val errorType: ErrorType, val errorMessage: String): AuthUiState
}

class LoginViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Default)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun reset() {
        _uiState.value = AuthUiState.Default
    }

    fun login(emailString: String, passwordString: String) {
        when {
            !Patterns.isValidEmail(emailString) -> _uiState.value = AuthUiState.Failure(ErrorType.INVALID_EMAIL, "")
            !Patterns.isValidPassword(passwordString) -> _uiState.value = AuthUiState.Failure(ErrorType.INVALID_PASSWORD, "")
            else -> {
                _uiState.value = AuthUiState.Loading

                viewModelScope.launch {
                    val result = authRepository.loginWithEmailAndPassword(
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
    }

    companion object {
        fun provideFactory(
            authRepository: AuthRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return LoginViewModel(authRepository) as T
            }
        }
    }
}