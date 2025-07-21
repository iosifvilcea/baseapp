
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import baseapp.composeapp.generated.resources.Res
import baseapp.composeapp.generated.resources.email_address
import baseapp.composeapp.generated.resources.reset_password_prompt
import com.blankthings.baseapp.component.BaOutlinedButton
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ForgotPasswordScreen(onForgotPasswordClick: () -> Unit) {
    val email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxHeight()
            .padding(40.dp)
    ) {

        Text(
            text = stringResource(Res.string.reset_password_prompt),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            leadingIcon = { Icon(Icons.Default.Info, contentDescription = "") },
            label = { Text(text = stringResource(Res.string.email_address)) },
            modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp)
        )

        BaOutlinedButton(
            text = "Send",
            onClick = onForgotPasswordClick
        )
    }
}

@Preview
@Composable
fun ForgotPasswordPreview() {
    MaterialTheme {
        ForgotPasswordScreen { }
    }
}