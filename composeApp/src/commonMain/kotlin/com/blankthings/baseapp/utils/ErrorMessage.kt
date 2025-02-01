package com.blankthings.baseapp.utils

import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.error_invalid_email
import baseapp.composeapp.generated.resources.error_invalid_password_length
import baseapp.composeapp.generated.resources.error_server_failed
import org.jetbrains.compose.resources.StringResource

enum class ErrorType(val message: StringResource) {
    INVALID_EMAIL(Res.string.error_invalid_email),
    INVALID_PASSWORD(Res.string.error_invalid_password_length),
    SERVER(Res.string.error_server_failed),
}