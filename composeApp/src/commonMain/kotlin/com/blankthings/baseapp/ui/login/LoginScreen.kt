package com.blankthings.baseapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import baseapp.composeapp.generated.resources.BLANK
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.login
import baseapp.composeapp.generated.resources.password
import baseapp.composeapp.generated.resources.username
import com.blankthings.baseapp.component.AppIcons
import com.blankthings.baseapp.component.BaOutlinedButton
import com.blankthings.baseapp.model.AuthData
import com.blankthings.baseapp.utils.dismissibleKeyboard
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun loginScreen(
    loginViewModel: LoginViewModel,
    showLoadingScreen: Boolean = false
) {
    LoginScreen(onLoginClicked = loginViewModel::login)
    if (showLoadingScreen) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)), // Semi-transparent overlay
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }
}

@Composable
fun LoginScreen(onLoginClicked: (AuthData) -> Unit = { _ -> }) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    val keyboardActions = KeyboardActions(
        onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
    })

    val blank = stringResource(Res.string.BLANK)
    val usernameLabel = stringResource(Res.string.username)
    val passwordLabel = stringResource(Res.string.password)

    val username = remember { mutableStateOf(blank) }
    val password = remember { mutableStateOf(blank) }

    Column(modifier = Modifier.fillMaxHeight().padding(40.dp).dismissibleKeyboard()) {
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            leadingIcon = { Icon(AppIcons.Person, contentDescription = usernameLabel) },
            label = { Text(text = usernameLabel) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp),
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            leadingIcon = { Icon(AppIcons.Info, contentDescription = passwordLabel) },
            label = { Text(text = passwordLabel) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )

        BaOutlinedButton(
            text = stringResource(Res.string.login),
            onClick = { onLoginClicked.invoke(AuthData(username.value, password.value)) }
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}