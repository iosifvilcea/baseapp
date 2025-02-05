package com.blankthings.baseapp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalFocusManager

fun Modifier.dismissibleKeyboard(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        focusManager.clearFocus()
    }
}