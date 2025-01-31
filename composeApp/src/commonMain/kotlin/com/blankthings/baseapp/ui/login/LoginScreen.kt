package com.blankthings.baseapp.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import baseapp.composeapp.generated.resources.BLANK
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.create_account
import baseapp.composeapp.generated.resources.forgot_password
import baseapp.composeapp.generated.resources.login
import baseapp.composeapp.generated.resources.password
import baseapp.composeapp.generated.resources.username
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    authUiState: AuthUiState,
    onForgotAccountClicked: () -> Unit = {},
    onLoginClicked: (String, String) -> Unit = { _, _ -> },
    onCreateAccountClicked: () -> Unit = {},
) {
    val blank = stringResource(Res.string.BLANK)
    val usernameLabel = stringResource(Res.string.username)
    val passwordLabel = stringResource(Res.string.password)

    val username = remember { mutableStateOf(blank) }
    val password = remember { mutableStateOf(blank) }

    Column(modifier = Modifier.fillMaxHeight().padding(40.dp)) {
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = usernameLabel) },
            label = { Text(text = usernameLabel) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp)
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            leadingIcon = { Icon(Icons.Default.Info, contentDescription = passwordLabel) },
            label = { Text(text = passwordLabel) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        TextButton(
            onClick = onForgotAccountClicked,
            modifier = Modifier.wrapContentSize().padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = stringResource(Res.string.forgot_password),
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

        OutlinedButton(
            onClick = { onLoginClicked.invoke(username.value, password.value) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp)) {
            Text(
                text = stringResource(Res.string.login),
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

        OutlinedButton(
            onClick = onCreateAccountClicked,
            modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp)) {
            Text(
                text = stringResource(Res.string.create_account),
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}