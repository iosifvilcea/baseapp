package com.blankthings.baseapp.data

sealed class LoginResult {
    data class Success(val accessToken: String, val uuid: String, val isNewAccount: Boolean) : LoginResult()
    data class Failed(val messageId: String?, val message: String) : LoginResult()
}
